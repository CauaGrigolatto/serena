package br.com.cauag.serena.core.syntax;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.Condition;

public abstract class FunctionChain implements FunctionExecutor {
	private Map<String, FunctionChain> successors;
	private List<Condition> executeConditions;
	
	protected FunctionChain() {
		this.successors = new HashMap<String, FunctionChain>();
		this.executeConditions = new LinkedList<Condition>();
	}
	
	public int handleNextToken(String[] tokens, int index, Core core) throws Exception {
		if (!canExecuteUnderConditions(core)) {
			return core.index;
		}
		
		String token = null;
		
		if (index < tokens.length) {
			token = tokens[index];
			
			if (token != null ) {
				token = token.trim();
				FunctionChain next = successors.get(token);
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
	
	protected void addSuccessor(String name, FunctionChain successor) {
		this.successors.put(name, successor);
	}
	
	protected void executeIf(Condition condition) {
		executeConditions.add(condition);
	}
	
	private boolean canExecuteUnderConditions(Core core) {
		boolean canExecute = true;
		int i = 0;
		
		while (i < executeConditions.size() && canExecute) {
			Condition condition = executeConditions.get(i);
			canExecute = condition.check(core);
			i++;
		}
		
		return canExecute;
	}
}
