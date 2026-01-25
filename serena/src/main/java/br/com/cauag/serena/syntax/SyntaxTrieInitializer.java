package br.com.cauag.serena.syntax;

import br.com.cauag.serena.syntax.assign.Assign;
import br.com.cauag.serena.syntax.foreach.For;
import br.com.cauag.serena.syntax.screenshot.Screenshot;
import br.com.cauag.serena.syntax.set.Set;
import br.com.cauag.serena.syntax.type.Type;

public class SyntaxTrieInitializer extends NotExecutable {
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
		addSuccessor("FOR", new For());
		addSuccessor("SCREENSHOT", new Screenshot());
	}
}
