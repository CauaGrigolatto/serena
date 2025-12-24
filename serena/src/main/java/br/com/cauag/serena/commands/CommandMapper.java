package br.com.cauag.serena.commands;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CommandMapper {
	
	private Map<Command, CommandExecutor> map;
	
	public CommandMapper() {
		this.map = new HashMap<CommandMapper.Command, CommandExecutor>();
		map.put(Command.EXECUTE, new Execute());
		map.put(Command.MOVE_MOUSE, new MoveMouse());
		map.put(Command.LEFT_CLICK, new LeftClick());
		map.put(Command.RIGHT_CLICK, new RightClick());
		map.put(Command.WAIT_SECONDS, new WaitSeconds());
		map.put(Command.WAIT_MILLIS, new WaitMillis());
		map.put(Command.TYPE, new Type());
		map.put(Command.PRESS, new Press());
	}
	
	public CommandExecutor fromString(String command) {
		for (Command c : Command.values()) {
			if (c.toString().equals(command)) {
				return get(c);
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public CommandExecutor get(Command command) {
		try {			
			return map.get(command).getClass().newInstance();
		}
		catch(IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public enum Command {		
		EXECUTE,
		MOVE_MOUSE,
		LEFT_CLICK,
		RIGHT_CLICK,
		WAIT_SECONDS,
		WAIT_MILLIS,
		TYPE,
		PRESS;
	}
	
	public enum SpecialKey {
		ENTER(KeyEvent.VK_ENTER),
		SPACE(KeyEvent.VK_SPACE),
		BACKSPACE(KeyEvent.VK_BACK_SPACE),
		TAB(KeyEvent.VK_TAB);
		
		private int keyCode;
		
		private SpecialKey(int keyCode) {
			this.keyCode = keyCode;
		}
		
		public int getKeyCode() {
			return keyCode;
		}
	}
}
