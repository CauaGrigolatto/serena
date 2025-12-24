package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;
import br.com.cauag.serena.functions.FunctionExecutor;
import br.com.cauag.serena.functions.FunctionMapper;
import br.com.cauag.serena.functions.FunctionStack;

public class Core implements Runnable {
	private final String FILE_EXTENSION = "ser";
	
	private final FunctionMapper functionMapper = new FunctionMapper();
	private final CommandMapper commandMapper = new CommandMapper();
	
	private final FunctionStack functionStack = new FunctionStack();
	
	private Robot bot;
	private File file;
	
	public Core(String path) throws Exception {
		this.bot = new Robot();
		setExecutable(path);
	}
	
	@Override
	public void run() {
		try(BufferedReader reader = new BufferedReader(new FileReader(file));) {
			String line;
			int index = 1;
						
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				
				if (line.isBlank() || line.startsWith("//")) continue;
								
				String[] statement = getStatement(line);
				
				String commandStr = statement[0].trim();
				String argumentStr = statement[1] != null ? statement[1].trim() : null;
				
				if ("END".equals(commandStr)) {
					functionStack.executeStack();
					continue;
				}
				
				FunctionExecutor functionExecutor = functionMapper.fromString(commandStr);
				
				if (functionExecutor != null) {
					functionExecutor.prepare(bot, argumentStr);
					functionStack.add(functionExecutor);
					continue;
				}
				
				CommandExecutor commandExecutor = commandMapper.fromString(commandStr);

				if (commandExecutor != null) {
					commandExecutor.prepare(argumentStr);
					
					if (! functionStack.isEmpty()) {
						functionStack.addCommand(commandExecutor);
					}
					else {
						commandExecutor.execute(bot);
					}
				}
				else {
					throw new IllegalArgumentException("Invalid command at line " + index + ": " + commandStr);
				}
			}
			
			index++;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] getStatement(String line) {
		int n = line.length();
		int i = 0;

		while (i < n && line.charAt(i) != ' ') i++;
		
		String[] statement = new String[2];
		statement[0] = line.substring(0, i);
		statement[1] = i < n ? line.substring(i+1, n) : null;
		return statement;
	}

	private void setExecutable(String path) throws IOException {
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
		
		this.file = file;
	}
}
