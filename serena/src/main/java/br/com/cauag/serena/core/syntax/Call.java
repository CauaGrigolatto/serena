package br.com.cauag.serena.core.syntax;

import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

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
		List<String> allArgs = Core.getArgs(complement);
		
		String blockName = allArgs.get(0);
		
		List<String> funcArgs = new LinkedList<String>();
		
		for (int i = 1; i < allArgs.size(); i++) {
			String current = allArgs.get(i);
			funcArgs.add(applyParametersAndVariables(current, core));
		}
		
		return core.indexController.callBlock(blockName, funcArgs, core.index);
	}
}
