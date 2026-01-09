package br.com.cauag.serena.core.syntax;

import java.util.HashMap;
import java.util.Map;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.functions.FunctionExecutor;

public abstract class ReservedWord implements FunctionExecutor {
	private Map<String, ReservedWord> successors;
	
	protected ReservedWord() {
		this.successors = new HashMap<String, ReservedWord>();
	}
	
	public int handleNextToken(String[] tokens, int index, Core core) throws Exception {
		String token = null;
		
		if (index < tokens.length) {
			token = tokens[index];
			
			if (token != null ) {
				token = token.trim();
				ReservedWord next = successors.get(token);
				if (next != null) {				
					return next.handleNextToken(tokens, index+1, core);
				}
			}
			
		}
		
		if (! canExecute()) {
			throw new IllegalArgumentException("syntax error son at line " + (core.index+1));
		}
		
		token = mergeTokens(tokens, index);
		
		return executeAndGetIndex(token, core);		
	}
	
	private String mergeTokens(String[] tokens, int index) {
		StringBuilder sb = new StringBuilder();
		int n = tokens.length;
		
		while (index < n) {
			sb.append(tokens[index]);
			index++;
		}
		
		return sb.toString();
	}

	protected abstract boolean canExecute();
	
	protected void addSuccessor(String name, ReservedWord successor) {
		this.successors.put(name, successor);
	}
}
