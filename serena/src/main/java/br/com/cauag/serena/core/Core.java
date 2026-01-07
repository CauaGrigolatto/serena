package br.com.cauag.serena.core;

import java.awt.Robot;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.core.functions.FunctionExecutor;
import br.com.cauag.serena.core.functions.FunctionMapper;
import br.com.cauag.serena.exceptions.InvalidCommandException;
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
	public final FunctionMapper functionMapper;
	
	public int index;
	
	public Core(String path) throws Exception {
		setExecutable(path);
		this.bot = new Robot();
		this.indexController = new IndexController();
		this.scheduleController = new ScheduleController();
		this.configController = new ConfigController();
		this.variablesController = new VariablesController();
		this.functionMapper = new FunctionMapper();
	}
	
	public void run() {
		try {
			if (fileLines == null) {				
				fileLines = FileUtils.readLines(file, "UTF-8");
			}
			
			for (; index < fileLines.size(); index++) {
				String line = fileLines.get(index).trim();
				if (line.isBlank() || line.startsWith("//")) continue;
				
				String[] statement = getStatement(line);
				
				String commandStr = statement[0].trim();
				String complementStr = statement[1] != null ? statement[1].trim() : null;
				
				commandStr = applyParametersAndVariables(commandStr);
				complementStr = applyParametersAndVariables(complementStr);
				
				try {
					FunctionExecutor functionExecutor = functionMapper.fromString(commandStr);
					
					if (functionExecutor != null) {
						int temp = functionExecutor.executeAndGetIndex(complementStr, this);							

						if (temp > -2) {
							index = temp;
						}
						else {
							break;
						}
					}
					else {
						throw new InvalidCommandException(commandStr, index+1);
					}
				}
				catch(Exception e) {
					e.printStackTrace();					
					return;
				}
			}
			
			scheduleController.run(this);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String applyParametersAndVariables(String target) {
		if (target != null && ! target.isBlank()) {			
			Map<String, String> currentArgs = indexController.currentArgs();
			
			if (currentArgs != null) {
				for (Map.Entry<String, String> currArg : currentArgs.entrySet()) {
					String argKey = currArg.getKey();
					String argValue = currArg.getValue();
					target = target.replaceAll("\\$" + argKey, argValue);
				}
			}
			
			Map<String, String> currentVariables = variablesController.variables();
			
			if (currentVariables != null) {
				for (Map.Entry<String, String> variable : currentVariables.entrySet()) {
					String varName = variable.getKey();
					String varValue = variable.getValue();
					target = target.replaceAll("\\$" + varName, varValue);
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
