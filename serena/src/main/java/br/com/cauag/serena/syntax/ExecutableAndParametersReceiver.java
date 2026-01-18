package br.com.cauag.serena.syntax;

import java.util.Map;

import br.com.cauag.serena.core.Core;

public abstract class ExecutableAndParametersReceiver extends Executable {
	protected String applyParametersAndVariables(String token, Core core) {		
		if (token != null && ! token.isBlank()) {
			Map<String, String> currentArgs = core.indexController.currentArgs();
			
			token = token.replaceAll("\\\\", "\\\\\\\\");
			
			if (currentArgs != null) {
				for (Map.Entry<String, String> currArg : currentArgs.entrySet()) {
					String argKey = currArg.getKey();
					String argValue = currArg.getValue();
					token = token.replaceAll("\\$" + argKey, argValue);
				}
			}
			
			Map<String, String> currentVariables = core.variablesController.variables();
			
			if (currentVariables != null) {
				for (Map.Entry<String, String> variable : currentVariables.entrySet()) {
					String varName = variable.getKey();
					String varValue = variable.getValue();
					token = token.replaceAll("\\$" + varName, varValue);
				}
			}
			
			Map<String, String> forEachVariables = core.forEachController.variables();
			
			if (forEachVariables != null) {
				for (Map.Entry<String, String> variable : forEachVariables.entrySet()) {
					String varName = variable.getKey();
					String varValue = variable.getValue();
					token = token.replaceAll("\\$" + varName, varValue);
				}
			}
		}
		
		return token;
	}
}
