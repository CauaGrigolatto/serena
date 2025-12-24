package br.com.cauag.serena.commands;

import java.awt.Robot;

import br.com.cauag.serena.commands.CommandMapper.SpecialKey;

public class Press implements CommandExecutor {

	private SpecialKey key;
	
	@Override
	public void prepare(String arg) {
		this.key = SpecialKey.valueOf(arg);
	}

	@Override
	public void execute(Robot bot) {
		bot.keyPress(key.getKeyCode());
	}
	
}
