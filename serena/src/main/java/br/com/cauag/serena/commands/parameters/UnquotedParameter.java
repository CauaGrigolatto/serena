package br.com.cauag.serena.commands.parameters;

public class UnquotedParameter extends Parameter<String> {

	public UnquotedParameter(String value) {
		super(value);
	}
	
	@Override
	protected void validate(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Parameter value should not be null.");
		}
				
		boolean hasQuotes = value.matches(".*['\"].*");

		if (hasQuotes) {
			throw new IllegalArgumentException("Parameter value should be unquoted.");
		}
	}
}
