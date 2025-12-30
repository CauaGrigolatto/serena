package br.com.cauag.serena.commands;

import java.awt.Robot;

import br.com.cauag.serena.commands.CommandMapper.SpecialKey;
import br.com.cauag.serena.commands.parameters.UnquotedParameter;

public class Press implements CommandExecutor {	
	private UnquotedParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new UnquotedParameter(arg);
	}

	@Override
	public void execute(Robot bot) {
		SpecialKey key = SpecialKey.valueOf( param.getValue() );
		bot.keyPress( key.getKeyCode() );
	}
}
