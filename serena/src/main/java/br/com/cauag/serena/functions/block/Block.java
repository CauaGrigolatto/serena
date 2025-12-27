package br.com.cauag.serena.functions.block;

import java.awt.Robot;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.ParameterizedCommand;

public class Block {
	private String name;
	private List<CommandExecutor> commands;
	
	private Map<String, String> argumentsMap;
	private String[] argumentsArr;
	
	public Block(String name, String... args) {
		this.commands = new LinkedList<CommandExecutor>();
		this.argumentsMap = new HashMap<String, String>();
		setName(name);
		initArguments(args);
	}
	
	public void addCommand(CommandExecutor command) {
		this.commands.add(command);
	}

	public void addCommands(List<CommandExecutor> commands) {
		for (CommandExecutor c : commands) {
			this.commands.add(c);
		}
	}
	
	public List<CommandExecutor> getCommands() {
		return commands;
	}
	
	private void initArguments(String...args) {
		if (args != null) {			
			int n = args.length;
			
			this.argumentsArr = new String[n];
			
			for (int i = 0; i < n; i++) {
				String arg = args[i];
				
				if (argumentsMap.containsKey(arg)) {
					throw new IllegalArgumentException("Duplicated argument declared: " +  arg + ".");
				}
				
				argumentsMap.put(arg, null);
				argumentsArr[i] = arg;
			}
		}
	}

	public void setArguments(String...args) {
		if (args != null) {
			for (int i = 0; i < argumentsArr.length; i++) {
				String argName = argumentsArr[i];
				String argValue = args[i];
				argumentsMap.put(argName, argValue);
			}
			
			for (CommandExecutor c : commands) {
				if (c instanceof ParameterizedCommand pc) {					
					pc.applyParameters(argumentsMap);
				}
			}
		}
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public void execute(Robot bot) {
		for (CommandExecutor c : commands) {
			c.execute(bot);
		}
	}
	
	public Map<String, String> getParameters() {
		return argumentsMap;
	}
}
