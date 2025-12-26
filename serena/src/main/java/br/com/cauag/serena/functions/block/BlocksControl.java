package br.com.cauag.serena.functions.block;

import java.awt.Robot;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.cauag.serena.commands.CommandExecutor;

public class BlocksControl {
	private final Map<String, List<CommandExecutor>> definedBlocks;
	
	private final Robot bot;
	
	private String blockName;
	private List<CommandExecutor> blockCommands;
	private boolean declaringBlock;
	
	public BlocksControl(Robot bot) {
		this.bot = bot;
		this.definedBlocks = new HashMap<String, List<CommandExecutor>>();
	}
	
	public void execute(String blockName) {
		List<CommandExecutor> commands = getBlock(blockName);
		
		for (CommandExecutor c : commands) {
			c.execute(bot);
		}
	}
	
	public void startBlock(String blockName) {
		this.blockName = blockName;
		this.blockCommands = new LinkedList<CommandExecutor>();
		this.declaringBlock = true;
	}
	
	public void appendCommand(CommandExecutor command) {
		this.blockCommands.add(command);
	}
	
	public void appendCommands(List<CommandExecutor> command) {
		this.blockCommands.addAll(command);
	}
	
	public void closeBlock() {
		definedBlocks.put(blockName, new LinkedList<>(blockCommands));
		this.blockName = null;
		this.blockCommands = null;
		this.declaringBlock = false;
	}
	
	public boolean isDeclaringBlock() {
		return declaringBlock;
	}
	
	public List<CommandExecutor> getBlock(String blockName) {
		return definedBlocks.get(blockName);
	}
}
