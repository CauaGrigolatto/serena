package br.com.cauag.serena.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VariablesController {
	private Map<String, String> variables;
	
	public VariablesController() {
		this.variables = new HashMap<String, String>();
	}
	
	public void setVariable(String name, String value) {
		this.variables.put(name, value);
	}
	
	public String getValue(String varName) {
		return this.variables.get(varName);
	}
	
	public Map<String, String> variables() {
		if (variables == null) return null;
		return Collections.unmodifiableMap(variables);
	}
}
