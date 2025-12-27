package br.com.cauag.serena.commands;

import java.awt.Robot;

import br.com.cauag.serena.commands.CommandMapper.SpecialKey;

public class Press extends ParameterizedCommand {	
	@Override
	public void prepare(String arg) {
		setArg(arg);
	}

	@Override
	public void execute(Robot bot) {
		SpecialKey key = SpecialKey.valueOf(getArg());
		bot.keyPress( key.getKeyCode() );
	}
}
