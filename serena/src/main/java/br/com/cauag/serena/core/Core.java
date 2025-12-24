package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;
import br.com.cauag.serena.commands.CommandMapper.Command;

public class Core implements Runnable {
	private final String FILE_EXTENSION = "ser";
	private final CommandMapper commandMapper = new CommandMapper();
	
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
			
			while ((line = reader.readLine()) != null) {
				if (line.isBlank()) continue;
				
				String[] statement = getStatement(line);
				Command command = Command.valueOf(statement[0]);
				CommandExecutor executor = commandMapper.get(command);
				executor.prepare(statement[1]);
				executor.execute(bot);
			}
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
