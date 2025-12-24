package br.com.cauag.serena.core.commands;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Type implements CommandExecutor {

	private String phrase;
	
	@Override
	public void prepare(String arg) {
		int n = arg.length();
		this.phrase = arg.substring(1, n-1);
	}

	@Override
	public void execute(Robot bot) {
		for (char ch : phrase.toCharArray()) {
			if (ch == '\0') continue;
			
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
	        
	        if (KeyEvent.VK_END == keyCode) {
	            throw new RuntimeException("Key code not found for character '" + ch + "'");
	        }
	        
	        bot.keyPress(keyCode);
	        bot.keyRelease(keyCode);
		}
	}

}
