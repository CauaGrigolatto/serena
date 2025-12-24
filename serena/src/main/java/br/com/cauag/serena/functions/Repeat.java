package br.com.cauag.serena.functions;

import java.awt.Robot;
import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.commands.CommandExecutor;

public class Repeat implements FunctionExecutor {
	private int times;
	private Robot bot;
	
	private final List<CommandExecutor> commandsToExecute = new LinkedList<CommandExecutor>();
	
	@Override
	public void prepare(Robot bot, String... args) {
		setBot(bot);
		setTimes(Integer.parseInt(args[0]));
	}

	@Override
	public void execute() {
		for (int i = 1; i <= times; i++) {
			for (CommandExecutor command : commandsToExecute) {
				command.execute(bot);
			}
		}
	}

	@Override
	public void addCommand(CommandExecutor executor) {
		if (executor == null) {
			throw new IllegalArgumentException("Invalid command to execute.");
		}
		
		commandsToExecute.add(executor);
	}
	
	public void setBot(Robot bot) {
		this.bot = bot;
	}
	
	public void setTimes(int times) {
		if (times <= 0) {
			throw new IllegalArgumentException("Invalid number of the loop.");
		}
		
		this.times = times;
	}
}
