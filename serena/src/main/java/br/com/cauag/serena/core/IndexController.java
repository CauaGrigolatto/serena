package br.com.cauag.serena.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import br.com.cauag.serena.exceptions.BlockNotDeclaredException;
import br.com.cauag.serena.exceptions.DuplicatedBlockException;
import br.com.cauag.serena.exceptions.IncompatibleArgumentsException;
import br.com.cauag.serena.exceptions.NegativeArgumentException;

public class IndexController {
	private boolean declaringBlock;
	private final Map<String, Integer> blockIndexes;
	private final Map<String, List<String>> blockArgs;
	private final Stack<Map<String, String>> stackArgs;
	private final Stack<Integer> repeatingTimes;
	private final Stack<Integer> comebackIndexes;
		
	public IndexController() {
		this.blockIndexes = new HashMap<String, Integer>();
		this.blockArgs = new HashMap<String, List<String>>();
		this.comebackIndexes = new Stack<Integer>();
		this.stackArgs = new Stack<Map<String,String>>();
		this.repeatingTimes = new Stack<Integer>();
	}

	public void addBlock(int index, String blockName, List<String> args) throws DuplicatedBlockException {
		if (blockIndexes.containsKey(blockName)) {
			throw new DuplicatedBlockException(blockName);
		}
		
		blockIndexes.put(blockName, index);
		blockArgs.put(blockName, args);
		this.declaringBlock = true;
	}
	
	public int callBlock(String blockName, List<String> args, int currentIndex) throws BlockNotDeclaredException {
		comebackIndexes.add(currentIndex);
		
		Integer index = blockIndexes.get(blockName);
		
		if (index == null) {
			throw new BlockNotDeclaredException(blockName);
		}
		
		List<String> blockArgsList = blockArgs.get(blockName);
		int acceptedArgs = blockArgsList.size();
		int passedArgs = args.size();
		
		if (acceptedArgs != passedArgs) {
			throw new IncompatibleArgumentsException(blockName, acceptedArgs, passedArgs);
		}
		
		Map<String, String> currentArgsMap = new HashMap<String, String>();
		
		for (int i = 0; i < acceptedArgs; i++) {
			String keyArg = blockArgsList.get(i);
			String valueArg = args.get(i);
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

	public void addRepeat(String commandArgumentStr, int currentIndex) throws NegativeArgumentException {
		int times = Integer.parseInt(commandArgumentStr);
		
		if (times < 0) {
			throw new NegativeArgumentException();
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
