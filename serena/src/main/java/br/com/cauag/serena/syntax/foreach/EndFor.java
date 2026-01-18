package br.com.cauag.serena.syntax.foreach;

import br.com.cauag.serena.syntax.NotExecutable;

public class EndFor extends NotExecutable {
	public EndFor() {
		super();
		addSuccessor("EACH", new EndForEach());
	}
}
