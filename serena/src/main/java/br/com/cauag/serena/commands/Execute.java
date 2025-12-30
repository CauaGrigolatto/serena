package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.io.IOException;

import br.com.cauag.serena.commands.parameters.UnquotedParameter;

public class Execute implements CommandExecutor {
	private UnquotedParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new UnquotedParameter(arg);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(Robot bot) {
		try {			
			Runtime.getRuntime().exec(param.getValue());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
