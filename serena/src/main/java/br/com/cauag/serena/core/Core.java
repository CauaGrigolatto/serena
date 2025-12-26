package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;
import br.com.cauag.serena.functions.block.BlocksControl;
import br.com.cauag.serena.functions.repeat.RepeatsControl;
import br.com.cauag.serena.functions.repeat.Syntax;

public class Core implements Runnable {
	private Robot bot;
	private File file;
	
	private final String FILE_EXTENSION = "ser";
	
	private final CommandMapper commandMapper;
		
	private final BlocksControl blocksControl;
	private final RepeatsControl repeatsControl;
	
	public Core(String path) throws Exception {
		this.bot = new Robot();
		this.commandMapper = new CommandMapper();
		this.blocksControl = new BlocksControl(bot);
		this.repeatsControl = new RepeatsControl();
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
				String argumentStr = statement[1] != null ? statement[1].trim() : null;
				
				if (Syntax.BLOCK.sameAs(commandStr)) {
					blocksControl.startBlock(argumentStr);
				}
				else if (Syntax.END_BLOCK.sameAs(commandStr)) {
					blocksControl.closeBlock();
				}
				else if (Syntax.CALL.sameAs(commandStr)) {					
					if (blocksControl.isDeclaringBlock()) {
						blocksControl.appendCommands(blocksControl.getBlock(argumentStr));
					}
					else {						
						blocksControl.execute(argumentStr);
					}
				}
				else if (Syntax.REPEAT.sameAs(commandStr)) {
					repeatsControl.startRepeat(index, Integer.parseInt(argumentStr));
				}
				else if (Syntax.END_REPEAT.sameAs(commandStr)) {
					Integer goBackTo = repeatsControl.getIndexAndDecreaseLoop();
					
					if (goBackTo != null) {
						index = goBackTo;
					}
				}
				else {
					CommandExecutor commandExecutor = commandMapper.fromString(commandStr);
					
					if (commandExecutor != null) {
						commandExecutor.prepare(argumentStr);
						
						if (blocksControl.isDeclaringBlock()) {
							blocksControl.appendCommand(commandExecutor);
						}
						else {							
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

	private String[] getStatement(String line) {
		String[] statement = new String[2];
		String[] splittedLine = line.split(" ", 2);
		
		for (int i = 0; i < splittedLine.length; i++) {
			statement[i] = splittedLine[i];
		}
		
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
