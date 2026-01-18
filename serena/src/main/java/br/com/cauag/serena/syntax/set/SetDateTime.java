package br.com.cauag.serena.syntax.set;

import br.com.cauag.serena.syntax.NotExecutable;

public class SetDateTime extends NotExecutable {
	public SetDateTime() {
		super();
		addSuccessor("FORMAT", new SetDateTimeFormat());
	}
}
