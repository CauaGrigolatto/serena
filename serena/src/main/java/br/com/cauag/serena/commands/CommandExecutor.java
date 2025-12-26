package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.util.Map;

public interface CommandExecutor {
	void prepare(String arg);
	void execute(Robot bot);
	void applyParameters(Map<String, String> parameters);
}
