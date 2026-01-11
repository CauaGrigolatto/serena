package br.com.cauag.serena.core.syntax;

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
		String[][] extractedArgs = Core.extractArgs(complement);
		
		String blockName = extractedArgs[0][0];
		String[] args = extractedArgs[1];
		
		for (int i = 0; i < args.length; i++) {
			args[i] = applyParametersAndVariables(args[i], core);
		}
		
		return core.indexController.callBlock(blockName, args, core.index);
	}
}
