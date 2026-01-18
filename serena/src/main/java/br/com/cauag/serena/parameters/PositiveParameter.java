package br.com.cauag.serena.parameters;

public class PositiveParameter extends IntegerParameter {
	
	public PositiveParameter(Integer value) {
		super(value);
	}

	@Override
	protected void validate(Integer val) {
		super.validate(val);
		
		if (val < 0) {
			throw new IllegalArgumentException("Parameter must be a postive integer.");
		}
	}
	
}
