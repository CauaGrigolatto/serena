package br.com.cauag.serena.core.syntax;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.exceptions.InvalidSerenaFile;

public class Include extends ReservedWord {
	
	public Include() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		try {
			String filePath = QuotedParameter.valueOf(complement);
			File file = Core.validateAndGetFile(filePath);
			List<String> content = FileUtils.readLines(file, "UTF-8");
			core.fileLines.remove(core.index);
			core.fileLines.addAll(core.index, content);
			return core.index-1;
		}
		catch(InvalidSerenaFile isf) {
			throw new InvalidSerenaFile(core.index+1);
		}
	}
}
