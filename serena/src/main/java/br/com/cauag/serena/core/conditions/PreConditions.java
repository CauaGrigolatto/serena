package br.com.cauag.serena.core.conditions;

public class PreConditions {
	public static Condition NOT_WHEN_DECLARING_BLOCK = new NotWhenDeclaringBlock();
	public static Condition NOT_WHEN_SCHEDULING = new NotWhenScheduling();
}
