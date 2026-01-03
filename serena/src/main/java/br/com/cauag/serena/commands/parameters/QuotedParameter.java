package br.com.cauag.serena.commands.parameters;

public class QuotedParameter extends Parameter<String> {
	private String value;
	
	public QuotedParameter(String value) {
		super(value);
		this.value = value.substring(1, value.length()-1);
	}

	@Override
	protected void validate(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("Parameter value should not be null.");
		}
		
		boolean validRegex = value.matches("^\"[^\"]+\"$");
		
		if (! validRegex) {
			throw new IllegalArgumentException("Parameter value should start and end with doubled-quotes.");
		}
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
}
