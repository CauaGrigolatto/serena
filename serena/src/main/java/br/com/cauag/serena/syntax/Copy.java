package br.com.cauag.serena.syntax;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.QuotedParameter;

public class Copy extends ExecutableAndParametersReceiver  {
	public Copy() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String value = new QuotedParameter(complement).getValue();
		value = applyParametersAndVariables(value, core);
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection data = new StringSelection(value);
	    cb.setContents(data, null);
		return core.index;
	}
}
