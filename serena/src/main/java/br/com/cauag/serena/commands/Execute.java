package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.io.IOException;

public class Execute extends ParameterizedCommand {

	@Override
	public void prepare(String arg) {
		setArg(arg);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(Robot bot) {
		try {			
			Runtime.getRuntime().exec(getArg());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
