package br.com.cauag.serena.parameters;

public class IntegerParameter extends Parameter<Integer> {

	public IntegerParameter(Integer value) {
		super(value);
	}

	@Override
	protected void validate(Integer val) {
		if (val == null) {
			throw new IllegalArgumentException("Integer value should not be null.");
		}
	}
	
}
