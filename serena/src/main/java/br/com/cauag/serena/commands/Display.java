
package br.com.cauag.serena.commands;

import java.awt.Robot;

import br.com.cauag.serena.commands.parameters.QuotedParameter;

public class Display implements CommandExecutor {
	private QuotedParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new QuotedParameter(arg);
	}
	
	@Override
	public void execute(Robot bot) {
		int n = param.getValue().length();
		String message = param.getValue().substring(1, n-1);
		System.out.println( message );
	}

}
