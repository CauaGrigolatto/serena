package br.com.cauag.serena.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariablesController {
	private Map<String, String> variables;
	private Map<String, List<String>> lists;
	
	public VariablesController() {
		this.variables = new HashMap<String, String>();
		this.lists = new HashMap<String, List<String>>();
	}
	
	public void setVariable(String name, String value) {
		this.variables.put(name, value);
	}
	
	public String getValue(String varName) {
		return this.variables.get(varName);
	}
	
	public Map<String, String> variables() {
		return Collections.unmodifiableMap(variables);
	}

	public void setList(String varName, List<String> varValues) {
		this.lists.put(varName, varValues);
	}
	
	public Map<String, List<String>> lists() {
		return Collections.unmodifiableMap(lists);
	}
}
