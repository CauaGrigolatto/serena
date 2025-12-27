package br.com.cauag.serena.commands;

import java.util.Map;

public abstract class ParameterizedCommand implements CommandExecutor {

	private String arg;
	
	public void applyParameters(Map<String, String> parameters) {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = "\\$" + entry.getKey();
			String value = entry.getValue();
			
			// For nested params
			if (value != null && value.startsWith("$")) {
				value = "\\" + value;
			}
			
			this.arg = arg.replaceAll(key, value);
		}
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}
}
