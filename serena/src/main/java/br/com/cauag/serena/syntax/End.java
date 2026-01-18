package br.com.cauag.serena.syntax;

import br.com.cauag.serena.syntax.foreach.EndFor;

public class End extends NotExecutable {
	public End() {
		super();
		addSuccessor("BLOCK", new EndBlock());
		addSuccessor("REPEAT", new EndRepeat());
		addSuccessor("SCHEDULE", new EndSchedule());
		addSuccessor("FOR", new EndFor());
	}
}
