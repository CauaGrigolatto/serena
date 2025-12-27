package br.com.cauag.serena.commands;

import java.awt.Robot;

public interface CommandExecutor {
	void prepare(String arg);
	void execute(Robot bot);
}
