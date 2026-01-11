package br.com.cauag.serena.core.syntax;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Copy extends ParameterReceiver  {
	
	public Copy() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	protected boolean canExecute() {
		return true;
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
