package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.time.Duration;
import java.util.Map;

public class WaitMillis implements CommandExecutor {

	private int millis;
	
	@Override
	public void prepare(String arg) {
		this.millis = Integer.parseInt(arg);
	}

	@Override
	public void execute(Robot bot) {
		try {			
			Thread.sleep(Duration.ofMillis(millis));
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}
}
