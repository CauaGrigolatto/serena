package br.com.cauag.serena.conditions;

public class PreConditions {
	public static Condition NOT_WHEN_DECLARING_BLOCK = new NotWhenDeclaringBlock();
	public static Condition NOT_WHEN_SCHEDULING = new NotWhenScheduling();
	
	public static Condition NOT_WHEN_DECLARING_BLOCK_AND_THROWS(Exception e) {
		return new NotWhenDeclaringBlock(e);
	}
	
	public static Condition NOT_WHEN_SCHEDULING_AND_THROWS(Exception e) {
		return new NotWhenScheduling(e);
	}
}
