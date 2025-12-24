package br.com.cauag.serena.core.commands;

import java.awt.Robot;

public interface CommandExecutor {
	void prepare(String arg);
	void execute(Robot bot);
}
