package br.com.cauag.serena.functions;

import java.util.HashMap;
import java.util.Map;

public class FunctionMapper {
	private Map<FunctionMapper.Function, FunctionExecutor> map;
	
	public FunctionMapper() {
		this.map = new HashMap<FunctionMapper.Function, FunctionExecutor>();
		map.put(Function.REPEAT, new Repeat());
	}
	
	public FunctionExecutor fromString(String func) {
		for (Function f : Function.values()) {
			if (f.toString().equals(func)) {
				return get(f);
			}
		}
		
		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	public FunctionExecutor get(Function func) {
		try {
			return map.get(func).getClass().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public enum Function {
		REPEAT,
	}
}
