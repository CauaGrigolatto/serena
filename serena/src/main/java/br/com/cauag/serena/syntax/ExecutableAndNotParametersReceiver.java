package br.com.cauag.serena.syntax;

public abstract class ExecutableAndNotParametersReceiver extends NotParametersReceiver {
	@Override
	protected boolean canExecute() {
		return true;
	}
}
