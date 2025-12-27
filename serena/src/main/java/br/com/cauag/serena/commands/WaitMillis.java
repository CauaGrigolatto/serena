package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.time.Duration;

public class WaitMillis extends ParameterizedCommand {
	
	@Override
	public void prepare(String arg) {
		setArg(arg);
	}

	@Override
	public void execute(Robot bot) {
		try {			
			Thread.sleep(
				Duration.ofMillis(
					Integer.parseInt( getArg() )
				)
			);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
