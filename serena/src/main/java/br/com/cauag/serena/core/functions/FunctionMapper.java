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
		map.put(Syntax.SET, new Set());
		map.put(Syntax.ASSIGN, new Assign());
		map.put(Syntax.EXECUTE, new Execute());
		map.put(Syntax.MOVE_MOUSE, new MoveMouse());
		map.put(Syntax.LEFT_CLICK, new LeftClick());
		map.put(Syntax.RIGHT_CLICK, new RightClick());
		map.put(Syntax.WAIT_SECONDS, new WaitSeconds());
		map.put(Syntax.WAIT_MILLIS, new WaitMillis());
		map.put(Syntax.TYPE, new Type());
		map.put(Syntax.PRESS, new Press());
		map.put(Syntax.DISPLAY, new Display());
		map.put(Syntax.COPY, new Copy());
		map.put(Syntax.PASTE, new Paste());
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
