package br.com.cauag.serena.syntax.foreach;

import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.ExecutableAndNotParametersReceiver;

public class ForEach extends ExecutableAndNotParametersReceiver {
	public ForEach() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String[] args = complement.split("IN");
		String varName = args[0].trim();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			sb.append(arg);
		}
				
		List<String> datas = getList(sb.toString());
		
		core.forEachController.addForEach(core.index, varName, datas);
		
		return core.index;
	}
	
	private List<String> getList(String str) {				
		List<String> datas = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
				
		for (boolean canAppend = false; i < str.length(); i++) {
			if (str.charAt(i) == '"') {
				canAppend = !canAppend;
				
				if (! sb.isEmpty()) {
					datas.add(sb.toString());
					sb.setLength(0);
				}
			}
			else if (canAppend) {
				sb.append( str.charAt(i) );
			}
		}
		
		return datas;
	}
}
