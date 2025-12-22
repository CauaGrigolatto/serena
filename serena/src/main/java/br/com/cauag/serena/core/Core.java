package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FilenameUtils;

public class Core implements Runnable {
	private final String FILE_EXTENSION = "ser";

	private Robot bot;
	private File file;
	
	public Core(String executable) throws Exception {
		this.bot = new Robot();
		setExecutable(executable);
	}
	
	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			int lineIndex = 1;
			
			while ((line = reader.readLine()) != null) {
				String[] statement = line.split(" ");
				
				if (! (0 < statement.length || statement.length <= 2)) {
					throw new Exception("Invalid statement at line " + lineIndex + ": " + line);
				}
				
				String command = statement[0];
				String predicate = statement.length == 2 ? statement[1] : null;
				
				execute(command, predicate);
				
				lineIndex++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void execute(String commandStr, String predicate) throws Exception {
		if (commandStr.isBlank()) return;
		
		Command command;
		
		try {			
			command = Command.valueOf(commandStr); 
		}
		catch(IllegalArgumentException e) {
			throw new Exception(commandStr + " is not a valid command.");
		}
		
		switch (command) {
		case OPEN:
			Runtime.getRuntime().exec(predicate);
			break;
		case MOVE_MOUSE:
			String xStr = predicate.split(",")[0];
			String yStr = predicate.split(",")[1];
			
			int x = Integer.parseInt(xStr);
			int y = Integer.parseInt(yStr);
			
			bot.mouseMove(x, y);
			break;
		case LEFT_CLICK:
			bot.mousePress(0);
			break;
		case WAIT_MILLIS:
			long millis = Long.parseLong(predicate);
			Thread.sleep(Duration.ofMillis(millis));
			break;
		case WAIT_SECONDS:
			long seconds = Long.parseLong(predicate);
			Thread.sleep(Duration.ofSeconds(seconds));
			break;
		default:
			break;
		}
	}

	private void setExecutable(String executable) throws IOException {
		String extension = FilenameUtils.getExtension(executable);
		
		if (!FILE_EXTENSION.equals(extension)) {
			throw new IOException("Invalid serena script.");
		}
		
		File file = new File(executable);
		
		if (! file.exists()) {
			throw new IOException("Serena script does not exist.");
		}
		
		if (! file.isFile()) {
			throw new IOException("Argument must be the path to a script.");
		}
		
		this.file = file;
	}
}
