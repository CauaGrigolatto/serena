
package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.util.Map;

public class Display implements CommandExecutor {
	
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
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = "\\$" + entry.getKey();
			String value = entry.getValue();
			message = message.replaceAll(key, value);
		}
	}

	@Override
	public void execute(Robot bot) {
		System.out.println(message);
	}
}
