package br.com.cauag.serena.syntax;

public abstract class Executable extends FunctionChain {
	@Override
	protected boolean canExecute() {
		return true;
	}
}
