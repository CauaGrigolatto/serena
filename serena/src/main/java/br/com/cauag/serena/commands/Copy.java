package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Map;

public class Copy implements CommandExecutor {

	private String message;
	private int n;
	
	@Override
	public void prepare(String arg) {
		int argLen = arg.length();
		this.message = arg.substring(1, argLen-1);
		this.n = argLen-2;
	}
	
	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}

	@Override
	public void execute(Robot bot) {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection data = new StringSelection(message);
	    cb.setContents(data, null);
	}
}
