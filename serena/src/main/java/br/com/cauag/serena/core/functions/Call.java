package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class Call implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (! core.indexController.isDeclaringBlock() && ! core.scheduleController.isScheduling()) {						
			String[][] extractedArgs = Core.extractArgs(complement);
			
			String blockName = extractedArgs[0][0];
			String[] args = extractedArgs[1];
			
			return core.indexController.callBlock(blockName, args, core.index);
		}
		
		return core.index;
	}
}
