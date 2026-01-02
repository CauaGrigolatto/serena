package br.com.cauag.serena.core.functions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.cauag.serena.core.Core;

public class Include implements FunctionExecutor {

	@Override
	public int executeAndGetIndex(String complement, Core core) {
		try {
			File file = Core.validateAndGetFile(complement);
			List<String> content = FileUtils.readLines(file, "UTF-8");
			core.fileLines.remove(core.index);
			core.fileLines.addAll(core.index, content);
			return core.index-1;
		}
		catch(IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
