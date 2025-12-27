package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.io.IOException;
import java.util.Map;

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

	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}
}
