package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public interface FunctionExecutor {
	int executeAndGetIndex(String complement, Core core) throws Exception;
}
