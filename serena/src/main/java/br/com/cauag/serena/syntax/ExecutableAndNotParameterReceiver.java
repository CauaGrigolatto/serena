package br.com.cauag.serena.syntax;

public abstract class ExecutableAndNotParameterReceiver extends NotParametersReceiver {
	@Override
	protected boolean canExecute() {
		return true;
	}
}
