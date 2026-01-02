package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;
import br.com.cauag.serena.core.functions.FunctionExecutor;
import br.com.cauag.serena.core.functions.FunctionMapper;

public class Core {
	private Robot bot;
	private File file;
	
	private final static String FILE_EXTENSION = "ser";
	
	public List<String> fileLines;
	public final IndexController indexController;
	public final FunctionMapper functionMapper;
	public final CommandMapper commandMapper;
	public int index;
	
	public Core(String path) throws Exception {
		setExecutable(path);
		this.bot = new Robot();
		this.indexController = new IndexController();		
		this.functionMapper = new FunctionMapper();
		this.commandMapper = new CommandMapper();
	}
	
	public void run() {
		try {
			if (fileLines == null) {				
				fileLines = FileUtils.readLines(file, "UTF-8");
			}
			
			for (index = 0; index < fileLines.size(); index++) {
				String line = fileLines.get(index).trim();
				if (line.isBlank() || line.startsWith("//")) continue;
				
				String[] statement = getStatement(line);
				
				String commandStr = statement[0].trim();
				String complementStr = statement[1] != null ? statement[1].trim() : null;
				
				commandStr = applyParameters(commandStr);
				complementStr = applyParameters(complementStr);
				
				try {
					FunctionExecutor functionExecutor = functionMapper.fromString(commandStr);
					
					if (functionExecutor != null) {						
						index = functionExecutor.executeAndGetIndex(complementStr, this);
					}
					else {						
						CommandExecutor commandExecutor = commandMapper.fromString(commandStr);
						
						if (commandExecutor != null) {						
							if (! indexController.isDeclaringBlock()) {
								commandExecutor.prepare(complementStr);
								commandExecutor.execute(bot);
							}
						}
						else {
							throw new IllegalArgumentException(commandStr + " is not a command or function.");
						}
					}
				}
				catch(Exception e) {
					e.printStackTrace();					
					return;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String applyParameters(String target) {
		if (target != null && ! target.isBlank()) {			
			Map<String, String> currentArgs = indexController.currentArgs();
			
			if (currentArgs != null) {
				for (Map.Entry<String, String> currArg : currentArgs.entrySet()) {
					String argKey = currArg.getKey();
					String argValue = currArg.getValue();
					target = target.replaceAll("\\$" + argKey, argValue);
				}
			}
		}
		
		return target;
	}
	
	public static String[][] extractArgs(String commandArgument) {
		String[][] extracted = new String[2][2];
		
		String[] splittedArgs = commandArgument.split(" ");
		
		String name = splittedArgs[0];
		int totalArgs = splittedArgs.length-1;
		
		String[] args = new String[totalArgs];
		
		for (int i = 1; i <= totalArgs; i++) {
			args[i-1] = splittedArgs[i];
		}
		
		extracted[0] = new String[] {name};
		extracted[1] = args;
		return extracted;
	}
	
	private String[] getStatement(String line) {
		String[] statement = new String[2];
		String[] splittedLine = line.split(" ", 2);
		
		for (int i = 0; i < splittedLine.length; i++) {
			statement[i] = splittedLine[i];
		}
		
		return statement;
	}

	private void setExecutable(String path) throws IOException {
		this.file = validateAndGetFile(path);
	}
	
	public static File validateAndGetFile(String path) throws IOException {
		String extension = FilenameUtils.getExtension(path);
		
		if (!FILE_EXTENSION.equals(extension)) {
			throw new IOException("Invalid serena script.");
		}
		
		File file = new File(path);
		
		if (! file.exists()) {
			throw new IOException("Serena script does not exist.");
		}
		
		if (! file.isFile()) {
			throw new IOException("Argument must be the path to a script.");
		}
		
		return file;
	}
}
