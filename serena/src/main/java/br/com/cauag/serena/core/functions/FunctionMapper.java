package br.com.cauag.serena.core.functions;

import java.util.HashMap;
import java.util.Map;

public class FunctionMapper {
	private Map<Syntax, FunctionExecutor> map;
	
	public FunctionMapper() {
		this.map = new HashMap<Syntax, FunctionExecutor>();
		map.put(Syntax.BLOCK, new Block());
		map.put(Syntax.END_BLOCK, new EndBlock());
		map.put(Syntax.CALL, new Call());
		map.put(Syntax.REPEAT, new Repeat());
		map.put(Syntax.END_REPEAT, new EndRepeat());
		map.put(Syntax.INCLUDE, new Include());
		map.put(Syntax.SCHEDULE, new Schedule());
		map.put(Syntax.END_SCHEDULE, new EndSchedule());
		map.put(Syntax.USE, new Use());
	}
	
	public FunctionExecutor fromString(String syntax) {
		for (Syntax c : Syntax.values()) {
			if (c.toString().equals(syntax)) {
				return get(c);
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public FunctionExecutor get(Syntax syntax) {
		try {			
			return map.get(syntax).getClass().newInstance();
		}
		catch(IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
