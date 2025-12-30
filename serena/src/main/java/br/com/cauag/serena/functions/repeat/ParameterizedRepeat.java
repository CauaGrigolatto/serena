package br.com.cauag.serena.functions.repeat;

import java.util.Map;

public class ParameterizedRepeat {
	
	private String timesStr;
	private Integer times;
	private int index;
	
	public ParameterizedRepeat(String timesStr, int index) {
		this.timesStr = timesStr;
		this.index = index;
	}
	
	public void startRepeat(Map<String, String> paramMap) {
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			String param = entry.getKey();
			String value = entry.getValue();
			timesStr.replaceAll(param, value);
		}
		
		updateIndex();
	}
	
	/*
	 * Decrease times and return come-back index
	 */
	public int finishLoop() {
		if (index > 0) {
			index--;
			times--;
		}
		
		return index;
	}
	
	private void updateIndex() throws IllegalArgumentException {
		this.times = Integer.parseInt(timesStr);
		
		if (times < 0) {
			throw new IllegalArgumentException("Repeat times must be positive.");
		}
	}
	
	public boolean isRepeating() {
		return times != null && times > 0;
	}
}
