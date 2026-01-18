package br.com.cauag.serena.conditions;

import br.com.cauag.serena.core.Core;

public class NotWhenDeclaringBlock implements Condition {
	private Exception exception;
	
	public NotWhenDeclaringBlock() {
		
	}
	
	public NotWhenDeclaringBlock(Exception e) {
		setException(e);
	}
	
	@Override
	public boolean check(Core core) throws Exception {
		boolean isDeclaring = core.indexController.isDeclaringBlock();
		
		if (isDeclaring && exception != null) {
			throw exception;
		}
		
		
		return !isDeclaring;
	}
	
	@Override
	public void setException(Exception e) {
		this.exception = e;
	}
}
