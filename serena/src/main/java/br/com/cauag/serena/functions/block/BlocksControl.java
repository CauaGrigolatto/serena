package br.com.cauag.serena.functions.block;

import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import br.com.cauag.serena.commands.CommandExecutor;

public class BlocksControl {	
	private final Map<String, Block> blocks;
	private Block current;
	
	private final Robot bot;

	public BlocksControl(Robot bot) {
		this.bot = bot;		
		this.blocks = new HashMap<String, Block>();
		this.current = null;
	}
	
	public void execute(String blockName, String... args) {
		Block block = getBlock(blockName);
		block.setArguments(args);
		block.execute(bot);
	}
	
	public void startBlock(String blockName, String... args) {
		this.current = new Block(blockName, args);
	}
	
	public void addCommand(CommandExecutor command) {
		this.current.addCommand(command);
	}
	
	public void closeBlock() {
		this.blocks.put(current.getName(), current);
		this.current = null;
	}
	
	public boolean isDeclaringBlock() {
		return current != null;
	}
	
	public Block getBlock(String blockName) {
		return blocks.get(blockName);
	}

	public void merge(Block nestedBlock) {
		this.current.addCommands(nestedBlock.getCommands());
	}
}
