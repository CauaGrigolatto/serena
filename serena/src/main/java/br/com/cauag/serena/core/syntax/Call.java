package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Call extends ParameterReceiver {
	
	public Call() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (! core.indexController.isDeclaringBlock() && ! core.scheduleController.isScheduling()) {						
			String[][] extractedArgs = Core.extractArgs(complement);
			
			String blockName = extractedArgs[0][0];
			String[] args = extractedArgs[1];
			
			for (int i = 0; i < args.length; i++) {
				args[i] = applyParametersAndVariables(args[i], core);
			}
			
			return core.indexController.callBlock(blockName, args, core.index);
		}
		
		return core.index;
	}
}
