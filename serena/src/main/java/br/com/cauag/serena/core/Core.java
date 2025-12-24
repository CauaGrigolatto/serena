package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.commands.CommandExecutor;
import br.com.cauag.serena.commands.CommandMapper;

public class Core implements Runnable {
	private final String FILE_EXTENSION = "ser";
	
	private final CommandMapper commandMapper = new CommandMapper();
		
	private final Stack<Integer> indexesToComeBack = new Stack<Integer>();
	private final Stack<Integer> timesToRepeat = new Stack<Integer>();
	
	private Robot bot;
	private File file;
	
	public Core(String path) throws Exception {
		this.bot = new Robot();
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
				
				if ("REPEAT".equals(commandStr)) {
					indexesToComeBack.add(index);
					timesToRepeat.add(Integer.parseInt(argumentStr));
				}
				else if ("END".equals(commandStr) && !indexesToComeBack.isEmpty()) {
					int indexToComeBack = indexesToComeBack.peek();
					
					int times = timesToRepeat.pop() - 1;
					
					if (times > 0) {
						index = indexToComeBack;
						timesToRepeat.add(times);
					}
					else {
						indexesToComeBack.pop();
					}					
				}
				else {					
					CommandExecutor commandExecutor = commandMapper.fromString(commandStr);
					
					if (commandExecutor != null) {
						commandExecutor.prepare(argumentStr);
						commandExecutor.execute(bot);
					}
				}
			}
		}
		catch(IOException e) {
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
