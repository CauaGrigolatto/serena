package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;
import br.com.cauag.serena.commands.CommandMapper.Command;

public class Core implements Runnable {
	private final String FILE_EXTENSION = "ser";
	
	private final CommandMapper commandMapper = new CommandMapper();
	
	private Robot bot;
	private File file;
	
	private Queue<CommandExecutor> executorsQueue;
	private int times;
	
	public Core(String path) throws Exception {
		this.bot = new Robot();
		setExecutable(path);
		this.executorsQueue = new LinkedList<CommandExecutor>();
	}
	
	@Override
	public void run() {
		try(BufferedReader reader = new BufferedReader(new FileReader(file));) {
			String line;
			int index = 1;
						
			while ((line = reader.readLine()) != null) {
				if (line.isBlank() || line.startsWith("//")) continue;
				
				String[] statement = getStatement(line);
				
				String commandStr = statement[0];
				String argumentStr = statement[1];
				
				if (commandStr.equals("REPEAT")) {	
					int times = Integer.parseInt(argumentStr);
					
					if (times <= 0) {
						throw new IllegalArgumentException("Erro at line " + index + ". Invalid number of the loop.");
					}
					
					this.times = times;
					
					continue;
				}
				
				boolean addToQueue = commandStr.startsWith("\t");
				
				if (addToQueue) {
					commandStr = commandStr.substring(1);
				}
				
				Command command;
				
				try {
					command = Command.valueOf(commandStr);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Invalid command " + commandStr + " at line " + index + ". Skipping...");
					continue;
				}
				
				CommandExecutor executor = commandMapper.get(command);
				executor.prepare(argumentStr);
				
				if (addToQueue) {
					executorsQueue.add(executor);
				}
				else {
					executeQueue();
					executor.execute(bot);
				}
				
			}
			
			executeQueue();
			index++;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void executeQueue() {
		for (int i = 1; i <= this.times; i++) {
			for (CommandExecutor e : executorsQueue) {
				e.execute(bot);
			}
		}
		
		executorsQueue.clear();
		times = 0;
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
