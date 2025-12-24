package br.com.cauag.serena.functions;

import java.awt.Robot;

import br.com.cauag.serena.commands.CommandExecutor;

public interface FunctionExecutor {
	void prepare(Robot bot, String... args);
	void execute();
	void addCommand(CommandExecutor command);
}
