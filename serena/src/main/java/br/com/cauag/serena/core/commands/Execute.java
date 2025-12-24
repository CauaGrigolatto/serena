package br.com.cauag.serena.core.commands;

import java.awt.Robot;
import java.io.IOException;

public class Execute implements CommandExecutor {
	private String program;

	@Override
	public void prepare(String arg) {
		this.program = arg;
	}

	@Override
	public void execute(Robot bot) {
		try {			
			Runtime.getRuntime().exec(program);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
