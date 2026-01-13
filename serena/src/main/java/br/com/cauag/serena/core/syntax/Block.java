package br.com.cauag.serena.core.syntax;

import java.util.LinkedList;
import java.util.List;

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
		String[] splittedArgs = complement.split(" ");
		
		String blockName = splittedArgs[0];
		
		List<String> args = new LinkedList<String>();
		
		for (int i = 1; i < splittedArgs.length; i++) {
			args.add(splittedArgs[i]);
		}
		
		core.indexController.addBlock(core.index, blockName, args);
		return core.index;
	}
}
