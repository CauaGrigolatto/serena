package br.com.cauag.serena.core.syntax;

import java.util.HashMap;
import java.util.Map;

import br.com.cauag.serena.core.Core;

public abstract class AbstractFunctionExecutor implements FunctionExecutor {
	private Map<String, AbstractFunctionExecutor> successors;
	
	protected AbstractFunctionExecutor() {
		this.successors = new HashMap<String, AbstractFunctionExecutor>();
	}
	
	public int handleNextToken(String[] tokens, int index, Core core) throws Exception {
		String token = null;
		
		if (index < tokens.length) {
			token = tokens[index];
			
			if (token != null ) {
				token = token.trim();
				AbstractFunctionExecutor next = successors.get(token);
				if (next != null) {				
					return next.handleNextToken(tokens, index+1, core);
				}
			}
			
		}
		
		if (! canExecute()) {
			throw new IllegalArgumentException("syntax error son at line " + (core.index+1));
		}
		
		if (token != null) {
			token = mergeTokens(tokens, index);
			token = applyParametersAndVariables(token, core);			
		}
		return executeAndGetIndex(token, core);		
	}
	
	private String mergeTokens(String[] tokens, int index) {
		StringBuilder sb = new StringBuilder();
		int n = tokens.length;
		
		while (index < n) {
			sb.append(tokens[index]);
			index++;
			if (index < n) {
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	private String applyParametersAndVariables(String token, Core core) {
		return token;
	}
	
	protected abstract boolean canExecute();
	
	protected void addSuccessor(String name, AbstractFunctionExecutor successor) {
		this.successors.put(name, successor);
	}
}
