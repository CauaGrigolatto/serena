package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Copy extends ParameterizedCommand {
	@Override
	public void prepare(String arg) {
		int argLen = arg.length();
		setArg(arg.substring(1, argLen-1));
	}
	
	@Override
	public void execute(Robot bot) {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection data = new StringSelection(getArg());
	    cb.setContents(data, null);
	}
}
