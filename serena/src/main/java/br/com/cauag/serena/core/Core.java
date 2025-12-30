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
import br.com.cauag.serena.functions.IndexController;
import br.com.cauag.serena.functions.Syntax;

public class Core implements Runnable {
	private Robot bot;
	private File file;
	
	private final String FILE_EXTENSION = "ser";
	
	private final CommandMapper commandMapper;

	/*
	 * 
	 */
	
	private final IndexController indexController;
	
	/*
	 * 
	 */
	
	public Core(String path) throws Exception {
		this.bot = new Robot();
		this.commandMapper = new CommandMapper();
		this.indexController = new IndexController();
		setExecutable(path);
	}
	
	@Override
	public void run() {
		try {
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			int n = lines.size();
			
			for (int index = 0; index < n; index++) {
				String line = lines.get(index).trim();
				if (line.isBlank() || line.startsWith("//")) continue;
				
				String[] statement = getStatement(line);
				
				String commandStr = statement[0].trim();
				String commandArgumentStr = statement[1] != null ? statement[1].trim() : null;
				
				Map<String, String> currentArgs = indexController.currentArgs();
				
				if (currentArgs != null) {
					for (Map.Entry<String, String> currArg : currentArgs.entrySet()) {
						String argKey = currArg.getKey();
						String argValue = currArg.getValue();
						commandStr = commandStr.replaceAll("\\$" + argKey, argValue);
						
						if (commandArgumentStr != null) {							
							commandArgumentStr = commandArgumentStr.replaceAll("\\$" + argKey, argValue);
						}
					}
				}
				
				if (Syntax.INCLUDE.sameAs(commandStr)) {
					File other = validateAndGetFile(commandArgumentStr);
					List<String> content = FileUtils.readLines(other, "UTF-8");
					lines.remove(index);
					lines.addAll(index, content);
					n = lines.size();
					
					line = lines.get(index);
					statement = getStatement(line);
					commandStr = statement[0].trim();
					commandArgumentStr = statement[1] != null ? statement[1].trim() : null;
				}
				
				if (Syntax.BLOCK.sameAs(commandStr)) {
					String[][] extractedArgs = extractArgs(commandArgumentStr);
					
					String blockName = extractedArgs[0][0];
					String[] args = extractedArgs[1];
					
					indexController.addBlock(index, blockName, args);
				}
				else if (Syntax.END_BLOCK.sameAs(commandStr)) {
					int comeBackTo = indexController.endBlock();
					
					if (comeBackTo != -1) {
						index = comeBackTo;
					}
				}
				else if (Syntax.CALL.sameAs(commandStr)) {
					if (! indexController.isDeclaringBlock()) {						
						String[][] extractedArgs = extractArgs(commandArgumentStr);
						
						String blockName = extractedArgs[0][0];
						String[] args = extractedArgs[1];
						
						index = indexController.callBlock(blockName, args, index);
					}
				}
				else if (Syntax.REPEAT.sameAs(commandStr)) {
					if (! indexController.isDeclaringBlock()) {
						indexController.addRepeat(commandArgumentStr, index);
					}
				}
				else if (Syntax.END_REPEAT.sameAs(commandStr)) {
					int comeBackTo = indexController.endRepeat();
					
					if (comeBackTo != -1) {
						index = comeBackTo;
					}
				}
				else {
					CommandExecutor commandExecutor = commandMapper.fromString(commandStr);
					
					if (commandExecutor != null) {						
						if (! indexController.isDeclaringBlock()) {
							commandExecutor.prepare(commandArgumentStr);
							commandExecutor.execute(bot);
						}
					}
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String[][] extractArgs(String commandArgument) {
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
	
	private File validateAndGetFile(String path) throws IOException {
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
