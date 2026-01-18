package br.com.cauag.serena.syntax;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Include extends ParameterReceiver {
	
	public Include() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String filePath = QuotedParameter.valueOf(complement);
		filePath = applyParametersAndVariables(filePath, core);
		File file = Core.validateAndGetFile(filePath);
		List<String> content = FileUtils.readLines(file, "UTF-8");
		core.fileLines.remove(core.index);
		core.fileLines.addAll(core.index, content);
		return core.index-1;
	}
}
