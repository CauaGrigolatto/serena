package br.com.cauag.serena.syntax.set;

import br.com.cauag.serena.syntax.NotExecutable;

public class SetDate extends NotExecutable {
	public SetDate() {
		super();
		addSuccessor("TIME", new SetDateTime());
	}	
}
