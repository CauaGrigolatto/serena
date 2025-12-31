package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class Block implements FunctionExecutor {
	
	@Override
	public int executeAndGetIndex(String complement) {
		String[][] extractedArgs = Core.extractArgs(complement);
		
		String blockName = extractedArgs[0][0];
		String[] args = extractedArgs[1];
		
		Core.indexController.addBlock(Core.index, blockName, args);
		return Core.index;
	}
}
