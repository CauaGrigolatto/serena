package br.com.cauag.serena.core.conditions;

import br.com.cauag.serena.core.Core;

public class NotWhenScheduling implements Condition {
	private Exception exception;
	
	public NotWhenScheduling() {
		
	}
	
	public NotWhenScheduling(Exception e) {
		setException(e);
	}
	
	@Override
	public boolean check(Core core) throws Exception {
		boolean isScheduling = core.scheduleController.isScheduling();
		
		if (isScheduling && exception != null) {
			throw exception;
		}
		
		return !isScheduling;
	}

	@Override
	public void setException(Exception e) {
		this.exception = e;
	}
}
