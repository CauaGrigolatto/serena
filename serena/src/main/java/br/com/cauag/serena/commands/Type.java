package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Type extends ParameterizedCommand {
	
	private int argLen;
	
	@Override
	public void prepare(String arg) {
		int fullArgLen = arg.length();
		setArg(arg.substring(1, fullArgLen-1));
		this.argLen = fullArgLen - 2;
	}

	@Override
	public void execute(Robot bot) {
		int i = 0;
		
		String message = getArg();
		
		for (char ch : message.toCharArray()) {
			if (ch == '\0') continue;
			
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
	        
	        if (KeyEvent.VK_END == keyCode) {
	            throw new RuntimeException("Key code not found for character '" + ch + "'");
	        }
	        
	        bot.keyPress(keyCode);
	        bot.keyRelease(keyCode);
	        
	        if (i < argLen-1) bot.delay(50);
		}
	}
}
