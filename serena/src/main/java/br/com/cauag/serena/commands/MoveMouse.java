package br.com.cauag.serena.commands;

import java.awt.Robot;

public class MoveMouse extends ParameterizedCommand {	
	@Override
	public void prepare(String arg) {
		setArg(arg);
	}

	@Override
	public void execute(Robot bot) {
		String[] coordinates = getArg().split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		bot.mouseMove(x, y);
	}
}
