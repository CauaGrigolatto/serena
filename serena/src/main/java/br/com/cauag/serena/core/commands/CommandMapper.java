package br.com.cauag.serena.core.commands;

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
	}
	
	public CommandExecutor get(Command command) {
		return map.get(command);
	}
	
	public enum Command {		
		EXECUTE,
		MOVE_MOUSE,
		LEFT_CLICK,
		RIGHT_CLICK,
		WAIT_SECONDS,
		WAIT_MILLIS,
		TYPE;
	}
}
