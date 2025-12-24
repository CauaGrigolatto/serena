package br.com.cauag.serena.commands;

import java.awt.Robot;

public class MoveMouse implements CommandExecutor {

	private int x;
	private int y;
	
	@Override
	public void prepare(String arg) {
		String[] coordinates = arg.split(",");
		this.x = Integer.parseInt(coordinates[0]);
		this.y = Integer.parseInt(coordinates[1]);
	}

	@Override
	public void execute(Robot bot) {
		bot.mouseMove(x, y);
	}

}
