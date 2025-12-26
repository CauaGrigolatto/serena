package br.com.cauag.serena.functions.repeat;

import java.util.Stack;

public class RepeatsControl {
	private final Stack<Integer> indexesToComeBack;
	private final Stack<Integer> timesToRepeat;

	public RepeatsControl() {
		this.indexesToComeBack = new Stack<Integer>();
		this.timesToRepeat = new Stack<Integer>();
	}
	
	public void startRepeat(int startIndex, int times) {
		indexesToComeBack.push(startIndex);
		timesToRepeat.push(times);
	}
	
	public Integer getIndexAndDecreaseLoop() {
		if (! isRepeating()) {
			throw new IllegalArgumentException("No REPEAT statement is open.");
		}
		
		int times = timesToRepeat.pop() - 1;
		int index = indexesToComeBack.peek();
		
		if (times > 0) {
			timesToRepeat.add(times);
			return index;
		}
		
		indexesToComeBack.pop();		
		return null;
	}
	
	public boolean isRepeating() {
		return !indexesToComeBack.isEmpty();
	}
}
