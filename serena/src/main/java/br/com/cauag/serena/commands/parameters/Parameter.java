package br.com.cauag.serena.commands.parameters;

public abstract class Parameter<T> {
	private T value;
	
	protected abstract void validate(T val);
	
	public Parameter(T value) {
		setValue(value);
	}
		
	public void setValue(T value) {
		validate(value);
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
}
