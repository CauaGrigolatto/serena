package br.com.cauag.serena.syntax;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.cauag.serena.core.Core;

public abstract class ExecutableAndParametersReceiver extends Executable {
	
	private final Pattern variablePattern = Pattern.compile("\\$[a-z][a-zA-Z0-9_]*", Pattern.CASE_INSENSITIVE);

	protected String applyParametersAndVariables(String token, Core core) {
		if (token != null && ! token.isBlank()) {
			token = token.replaceAll("\\\\", "\\\\\\\\");
			
			List<String> patterns = findPatterns(token);
			
			Map<String, String> forEachVariables = core.forEachController.variables();
			token = apply(token, patterns, forEachVariables);

			Map<String, String> blockArgs = core.indexController.currentArgs();
			token = apply(token, patterns, blockArgs);
			
			Map<String, String> globalArgs = core.variablesController.variables();
			token = apply(token, patterns, globalArgs);
		}
		
		return token;
	}
	
	protected String applyListParameters(String token, Core core) {
		if (token != null && ! token.isBlank()) {
			token = token.replaceAll("\\\\", "\\\\\\\\");
			
			List<String> patterns = findPatterns(token);
			
			Map<String, List<String>> lists = core.variablesController.lists();
			
			for (String pattern : patterns) {
				List<String> list = lists.get(pattern.substring(1));
				
				if (list != null) {
					StringBuilder sb = new StringBuilder();
					
					for (String el : list) {
						sb.append("\"" + el + "\"");
					}
					
					token = token.replace(pattern, sb.toString());
				}
			}
		}
		
		return token;
	}
	
	private String apply(String token, List<String> patterns, Map<String, String> variableMap) {
		if (variableMap != null && ! variableMap.isEmpty()) {			
			for (String pattern : patterns) {
				String value = variableMap.get(pattern.substring(1));
				
				if (value != null) {
					token = token.replace(pattern, value);
				}
			}
		}
		
		return token;
	}
	
	private List<String> findPatterns(String token) {
		Matcher matcher = variablePattern.matcher(token);
		List<String> recognizedPatterns = new LinkedList<String>();
		
		while (matcher.find()) {
			recognizedPatterns.add(matcher.group());
		}
		
		return recognizedPatterns;
	}
}
