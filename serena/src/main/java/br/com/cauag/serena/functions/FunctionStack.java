package br.com.cauag.serena.functions;

import java.util.Stack;

import br.com.cauag.serena.commands.CommandExecutor;

public class FunctionStack {
	private final Stack<FunctionExecutor> functionsToExecute = new Stack<FunctionExecutor>();
	
	public void add(FunctionExecutor func) {
		if (func == null) {
			throw new IllegalArgumentException();
		}
		
		functionsToExecute.add(func);
	}
	
	public void executeStack() {
		while (! functionsToExecute.isEmpty()) {
			functionsToExecute.pop().execute();
		}
	}
	
	public boolean isEmpty() {
		return functionsToExecute.isEmpty();
	}

	public void addCommand(CommandExecutor commandExecutor) {
		functionsToExecute.peek().addCommand(commandExecutor);
	}
}
