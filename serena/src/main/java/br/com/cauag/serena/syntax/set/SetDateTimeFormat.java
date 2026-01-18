package br.com.cauag.serena.syntax.set;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.ExecutableAndParametersReceiver;

public class SetDateTimeFormat extends ExecutableAndParametersReceiver {	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String value = QuotedParameter.valueOf(complement);
		value = applyParametersAndVariables(value, core);
		core.configController.setConfig("DATE_TIME_FORMAT", value);
		return core.index;
	}
}
