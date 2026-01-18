package br.com.cauag.serena.syntax;

import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.UnquotedParameter;

public class Block extends Executable {
	
	public Block() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK_AND_THROWS(
			new IllegalArgumentException("cannot declare blocks inside a BLOCK declaration.")
		));
		executeIf(PreConditions.NOT_WHEN_SCHEDULING_AND_THROWS(
			new IllegalArgumentException("cannot declare blocks inside a SCHEDULE declaration.")
		));
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String[] splittedArgs = complement.split(" ");
		
		String blockName = new UnquotedParameter( splittedArgs[0] ).getValue();
		
		List<String> args = new LinkedList<String>();
		
		for (int i = 1; i < splittedArgs.length; i++) {
			args.add( new UnquotedParameter(splittedArgs[i]).getValue() );
		}
		
		core.indexController.addBlock(core.index, blockName, args);
		return core.index;
	}
}
