package br.com.cauag.serena.core.conditions;

import br.com.cauag.serena.core.Core;

public class NotWhenScheduling implements Condition {
	@Override
	public boolean check(Core core) {
		return !core.scheduleController.isScheduling();
	}
}
