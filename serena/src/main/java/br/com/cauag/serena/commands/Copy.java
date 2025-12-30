package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import br.com.cauag.serena.commands.parameters.QuotedParameter;

public class Copy implements CommandExecutor  {
	private QuotedParameter param;
	
	@Override
	public void prepare(String arg) {
		param = new QuotedParameter(arg);
	}
	
	@Override
	public void execute(Robot bot) {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection data = new StringSelection(param.getValue());
	    cb.setContents(data, null);
	}
}
