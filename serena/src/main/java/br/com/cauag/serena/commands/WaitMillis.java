package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.time.Duration;

import br.com.cauag.serena.commands.parameters.PositiveParameter;

public class WaitMillis implements CommandExecutor {
	
	private PositiveParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new PositiveParameter(Integer.parseInt(arg));
	}

	@Override
	public void execute(Robot bot) {
		try {			
			Thread.sleep(
				Duration.ofMillis(
					param.getValue()
				)
			);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
