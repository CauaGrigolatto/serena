package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Block extends AbstractFunctionExecutor {
	
	public Block() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		//TODO no block declaration in block declarations
		String[][] extractedArgs = Core.extractArgs(complement);
		
		String blockName = extractedArgs[0][0];
		String[] args = extractedArgs[1];
		
		core.indexController.addBlock(core.index, blockName, args);
		return core.index;
	}
}
