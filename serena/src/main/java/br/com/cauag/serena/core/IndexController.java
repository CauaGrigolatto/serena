package br.com.cauag.serena.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IndexController {
	private boolean declaringBlock;
	
	private final Map<String, Integer> blocksIndexes;
	private final Map<String, String[]> blocksArgs;
	private final Stack<Map<String, String>> stackArgs;
	
	private final Stack<Integer> repeatingTimes;

	private final Stack<Integer> comebackIndexes;
	
	public IndexController() {
		this.blocksIndexes = new HashMap<String, Integer>();
		this.blocksArgs = new HashMap<String, String[]>();
		
		this.comebackIndexes = new Stack<Integer>();
		this.stackArgs = new Stack<Map<String,String>>();
		
		this.repeatingTimes = new Stack<Integer>();
	}

	public void addBlock(int index, String blockName, String[] args) throws IllegalArgumentException {
		if (blocksIndexes.containsKey(blockName)) {
			throw new IllegalArgumentException("Block named " + blockName + " is already declared.");
		}
		
		blocksIndexes.put(blockName, index);
		blocksArgs.put(blockName, args);
		this.declaringBlock = true;
	}
	
	public int callBlock(String blockName, String[] args, int currentIndex) {
		comebackIndexes.add(currentIndex);
		
		Integer index = blocksIndexes.get(blockName);
		
		if (index == null) {
			throw new IllegalArgumentException("Block named " + blockName + " does not exist.");
		}
		
		String[] blockArgs = blocksArgs.get(blockName);
		int acceptedArgumentsLen = blockArgs.length;
		int argumentsPassed = args.length;
		
		if (acceptedArgumentsLen != argumentsPassed) {
			throw new IllegalArgumentException(blockName + " must have " + acceptedArgumentsLen + " arguments, but " + argumentsPassed + " were passed.");
		}
		
		Map<String, String> currentArgsMap = new HashMap<String, String>();
		
		for (int i = 0; i < acceptedArgumentsLen; i++) {
			String keyArg = blockArgs[i];
			String valueArg = args[i];
			currentArgsMap.put(keyArg, valueArg);
		}
		
		stackArgs.push(currentArgsMap);
		
		return index;
	}
	
	public Integer endBlock() {
		if (! declaringBlock) {			
			stackArgs.pop();
			return comebackIndexes.pop();
		}
		
		declaringBlock = false;
		return null;
	}

	public Map<String, String> currentArgs() {
		if (stackArgs.isEmpty()) return null;
		return stackArgs.peek();
	}
	
	public boolean isDeclaringBlock() {
		return declaringBlock;
	}

	public void addRepeat(String commandArgumentStr, int currentIndex) {
		int times = Integer.parseInt(commandArgumentStr);
		
		if (times < 0) {
			throw new IllegalArgumentException("REPEAT does not accept negative values: " + times + ".");
		}
		
		repeatingTimes.push(times);
		comebackIndexes.push(currentIndex);
	}
	
	public Integer endRepeat() {
		if (! isDeclaringBlock()) {			
			int times = repeatingTimes.pop() - 1;
			
			if (times > 0) {			
				repeatingTimes.push(times);
				return comebackIndexes.peek();
			}
			
			comebackIndexes.pop();
		}
		
		return null;
	}
}
