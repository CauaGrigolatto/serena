package br.com.cauag.serena.commands;

import java.awt.Robot;

import br.com.cauag.serena.commands.parameters.QuotedParameter;

public class MoveMouse implements CommandExecutor {
	private QuotedParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new QuotedParameter(arg);
	}

	@Override
	public void execute(Robot bot) {
		String[] coordinates = param.getValue().split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		bot.mouseMove(x, y);
	}
}
