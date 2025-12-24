
package br.com.cauag.serena.commands;

import java.awt.Robot;

public class Display implements CommandExecutor {
	
	private String message;
	private int n;
	
	@Override
	public void prepare(String arg) {
		this.n = arg.length();
		this.message = arg.substring(1, n-1);
	}

	@Override
	public void execute(Robot bot) {
		System.out.println(message);
	}

}
