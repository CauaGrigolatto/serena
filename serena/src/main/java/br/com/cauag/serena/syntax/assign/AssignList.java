package br.com.cauag.serena.syntax.assign;

import java.util.LinkedList;
import java.util.List;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.ExecutableAndParametersReceiver;

public class AssignList extends ExecutableAndParametersReceiver {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String[] splittedArgs = complement.split(" ", 2);
		
		String varName = splittedArgs[0].trim();
		List<String> varValues = argsListOf(splittedArgs[1], core);
		
		core.variablesController.setList(varName, varValues);
		
		return core.index;
	}
	
	private List<String> argsListOf(String str, Core core) {
		List<String> args = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
				
		for (boolean canAppend = false; i < str.length(); i++) {
			if (str.charAt(i) == '"') {
				canAppend = !canAppend;
				
				if (! sb.isEmpty()) {
					args.add(applyParametersAndVariables(sb.toString(), core));
					sb.setLength(0);
				}
			}
			else if (canAppend) {
				sb.append( str.charAt(i) );
			}
		}
		
		return args;
	}
}
