package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Type implements CommandExecutor {

	private String phrase;
	private int n;
	
	@Override
	public void prepare(String arg) {
		this.n = arg.length();
		this.phrase = arg.substring(1, n-1);
	}

	@Override
	public void execute(Robot bot) {
		int i = 0;
		
		for (char ch : phrase.toCharArray()) {
			if (ch == '\0') continue;
			
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
	        
	        if (KeyEvent.VK_END == keyCode) {
	            throw new RuntimeException("Key code not found for character '" + ch + "'");
	        }
	        
	        bot.keyPress(keyCode);
	        bot.keyRelease(keyCode);
	        
	        if (i < n-1) bot.delay(50);
		}
	}
	
	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}
}
