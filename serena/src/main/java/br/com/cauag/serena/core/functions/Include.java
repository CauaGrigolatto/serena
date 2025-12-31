package br.com.cauag.serena.core.functions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.cauag.serena.core.Core;

public class Include implements FunctionExecutor {

	@Override
	public int executeAndGetIndex(String complement) {
		try {
			File file = Core.validateAndGetFile(complement);
			List<String> content = FileUtils.readLines(file, "UTF-8");
			Core.fileLines.remove(Core.index);
			Core.fileLines.addAll(Core.index, content);
			return Core.index-1;
		}
		catch(IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
}
