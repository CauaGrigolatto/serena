package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class Call implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement) {
		if (! Core.indexController.isDeclaringBlock()) {						
			String[][] extractedArgs = Core.extractArgs(complement);
			
			String blockName = extractedArgs[0][0];
			String[] args = extractedArgs[1];
			
			return Core.indexController.callBlock(blockName, args, Core.index);
		}
		
		return Core.index;
	}
}
