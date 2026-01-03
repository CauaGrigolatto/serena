package br.com.cauag.serena.core;

import java.util.HashMap;
import java.util.Map;

public class ConfigController {
	private Map<String, String> configValues;
	
	public ConfigController() {
		this.configValues = new HashMap<String, String>();
		this.configValues.put("DATE_TIME_FORMAT", "dd/MM/yyyy HH:mm");
	}
	
	public void setConfig(String config, String value) throws IllegalArgumentException {
		if (! configValues.containsKey(config)) {
			throw new IllegalArgumentException("config not found");
		}
		
		configValues.put(config, value);
	}
	
	public String getConfig(String config) {
		return configValues.get(config);
	}
}
