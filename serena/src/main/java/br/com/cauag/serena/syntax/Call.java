package br.com.cauag.serena.syntax;

import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Call extends ParameterReceiver {
	
	public Call() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		List<String> allArgs = getArgs(complement);
		
		String blockName = allArgs.get(0);
		
		List<String> funcArgs = new LinkedList<String>();
		
		for (int i = 1; i < allArgs.size(); i++) {
			String current = allArgs.get(i);
			funcArgs.add(applyParametersAndVariables(current, core));
		}
		
		return core.indexController.callBlock(blockName, funcArgs, core.index);
	}
	
	private List<String> getArgs(String str) {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		
		while (i < str.length() && str.charAt(i) != ' ') {
			sb.append(str.charAt(i));
			i++;
		}
		
		List<String> args = new LinkedList<String>();
		args.add(sb.toString());
		
		sb.setLength(0);
		
		for (boolean canAppend = false; i < str.length(); i++) {
			if (str.charAt(i) == '"') {
				canAppend = !canAppend;
				
				if (! sb.isEmpty()) {
					args.add(sb.toString());
					sb.setLength(0);
				}
			}
			else if (canAppend) {
				sb.append( str.charAt(i) );
			}
		}
		
		return args;
	}
}
