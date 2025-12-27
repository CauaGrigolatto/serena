
package br.com.cauag.serena.commands;

import java.awt.Robot;

public class Display extends ParameterizedCommand {
			
	@Override
	public void prepare(String arg) {
		int argLen = arg.length();
		setArg(arg.substring(1, argLen-1));
	}
	
	@Override
	public void execute(Robot bot) {
		System.out.println( getArg() );
	}

}
