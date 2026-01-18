package br.com.cauag.serena.syntax;

public class End extends NotExecutable {
	public End() {
		super();
		addSuccessor("BLOCK", new EndBlock());
		addSuccessor("REPEAT", new EndRepeat());
		addSuccessor("SCHEDULE", new EndSchedule());
	}
}
