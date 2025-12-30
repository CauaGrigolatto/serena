package br.com.cauag.serena.commands.parameters;

public class QuotedParameter extends Parameter<String> {
	
	public QuotedParameter(String value) {
		super(value);
	}

	@Override
	protected void validate(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Parameter value should not be null.");
		}
		
		boolean validValue = value.charAt(0) == '\"' && value.charAt(0) == '\"';
		
		if (! validValue) {
			throw new IllegalArgumentException("Parameter value should start and end with doubled-quotes.");
		}
	}
}
