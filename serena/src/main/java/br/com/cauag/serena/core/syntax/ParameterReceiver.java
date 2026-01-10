package br.com.cauag.serena.core.syntax;

public abstract class ParameterReceiver extends ReservedWord {
	@Override
	protected boolean canApplyParameters() {
		return true;
	}
}
