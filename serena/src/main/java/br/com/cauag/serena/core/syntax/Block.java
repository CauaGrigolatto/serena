package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Block extends FunctionChain {
	
	public Block() {
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
		//TODO no block declaration in block declarations
		String[][] extractedArgs = Core.extractArgs(complement);
		
		String blockName = extractedArgs[0][0];
		String[] args = extractedArgs[1];
		
		core.indexController.addBlock(core.index, blockName, args);
		return core.index;
	}
}
