package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.core.syntax.SyntaxTrieInitializer;
import br.com.cauag.serena.exceptions.IndexableException;
import br.com.cauag.serena.exceptions.InvalidSerenaFile;

public class Core {
	public final Robot bot;
	private File file;
	
	private final static String FILE_EXTENSION = "ser";
	
	public List<String> fileLines;
	public final IndexController indexController;
	public final ScheduleController scheduleController;
	public final ConfigController configController;
	public final VariablesController variablesController;
	
	//
	private final SyntaxTrieInitializer syntaxTrie;
	//
	
	public int index;
	
	public Core(String path) throws Exception {
		setExecutable(path);
		this.bot = new Robot();
		this.indexController = new IndexController();
		this.scheduleController = new ScheduleController();
		this.configController = new ConfigController();
		this.variablesController = new VariablesController();		
		this.syntaxTrie = new SyntaxTrieInitializer();
	}
	
	public void run() {
		try {
			if (fileLines == null) {				
				fileLines = FileUtils.readLines(file, "UTF-8");
			}
			
			for (; index < fileLines.size(); index++) {
				String line = fileLines.get(index).trim();
				if (line.isBlank() || line.startsWith("//")) continue;
				
				String[] tokens = line.split(" ");
				int tokenIndex = 0;
				
				index = syntaxTrie.handleNextToken(tokens, tokenIndex, this);
				if (index == -2) return;
			}
			
			scheduleController.run(this);
		}
		catch(Exception e) {
			throw new IndexableException(index+1, e.getMessage());
		}
	}
		
	private void setExecutable(String path) throws InvalidSerenaFile {
		this.file = validateAndGetFile(path);
	}
	
	public static File validateAndGetFile(String path) throws InvalidSerenaFile {
		File file = new File(path);

		if (! file.exists()) {
			throw new InvalidSerenaFile();
		}
		
		if (! file.isFile()) {
			throw new InvalidSerenaFile();
		}

		String extension = FilenameUtils.getExtension(path);
		
		if (!FILE_EXTENSION.equals(extension)) {
			throw new InvalidSerenaFile();
		}
		
		return file;
	}
}
