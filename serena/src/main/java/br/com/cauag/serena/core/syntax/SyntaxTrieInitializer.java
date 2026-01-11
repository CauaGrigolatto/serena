package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.syntax.type.Type;

public class SyntaxTrieInitializer extends AbstractFunctionExecutor {
	
	public SyntaxTrieInitializer() {
		super();
		addSuccessor("ASSIGN", new Assign());
		addSuccessor("BLOCK", new Block());
		addSuccessor("CALL", new Call());
		addSuccessor("CLICK", new Click());
		addSuccessor("COPY", new Copy());
		addSuccessor("DISPLAY", new Display());
		addSuccessor("END", new End());
		addSuccessor("EXECUTE", new Execute());
		addSuccessor("INCLUDE", new Include());
		addSuccessor("MOVE", new Move());
		addSuccessor("PASTE", new Paste());
		addSuccessor("PRESS", new Press());
		addSuccessor("REPEAT", new Repeat());
		addSuccessor("SCHEDULE", new Schedule());
		addSuccessor("SET", new Set());
		addSuccessor("TYPE", new Type());
		addSuccessor("WAIT", new Wait());
	}
	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		return -2;
	}

	@Override
	protected boolean canExecute() {
		return false;
	}
	
}
