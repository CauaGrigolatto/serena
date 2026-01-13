package br.com.cauag.serena.core.conditions;

import br.com.cauag.serena.core.Core;

public interface Condition {
	boolean check(Core core) throws Exception;
	void setException(Exception e);
}
