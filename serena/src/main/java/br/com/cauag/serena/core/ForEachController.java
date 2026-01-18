package br.com.cauag.serena.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ForEachController {
	private final Stack<Integer> repeatingTimes;
	private final Stack<Integer> comebackIndexes;
	
	private Stack<List<String>> datas;
	private Stack<Integer> currentIndex;
	private Map<String, String> entries;
	private Stack<String> varNames;
	
	public ForEachController() {
		this.datas = new Stack<List<String>>();
		this.repeatingTimes = new Stack<Integer>();
		this.comebackIndexes = new Stack<Integer>();
		this.currentIndex = new Stack<Integer>();
		this.entries = new HashMap<String, String>();
		this.varNames = new Stack<String>();
	}
	
	public void addForEach(int index, String varName, List<String> elements) {
		int times = elements.size();
		this.datas.push(elements);
		comebackIndexes.push(index);
		repeatingTimes.push(times);
		currentIndex.push(0);
		varNames.push(varName);
		entries.put(varName, elements.getFirst());
	}
	
	public Integer endForEach() {
		int times = repeatingTimes.pop() - 1;
		int index = currentIndex.pop() + 1;
		String key = varNames.peek();
		
		if (times > 0) {
			String value = datas.peek().get(index);
			entries.put(key, value);
			repeatingTimes.push(times);
			currentIndex.push(index);
			return comebackIndexes.peek();
		}
		
		comebackIndexes.pop();
		varNames.pop();
		datas.pop();
		entries.remove(key);
		
		return null;
	}
	
	public Map<String, String> variables() {
		return entries;
	}
}
