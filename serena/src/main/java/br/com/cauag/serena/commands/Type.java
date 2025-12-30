package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import br.com.cauag.serena.commands.parameters.QuotedParameter;

public class Type implements CommandExecutor {
	private QuotedParameter param;
	
	@Override
	public void prepare(String arg) {
		this.param = new QuotedParameter(arg);
	}

	@Override
	public void execute(Robot bot) {		
		String message = param.getValue();
		
		if (! message.matches("^\"[^\"]+\"$")) {
			throw new IllegalArgumentException("Mal formed quoted parameter.");
		}
		
		int messageLen = message.length()-2;
		
		for (int i = 1; i <= messageLen; i++) {
			char ch = message.charAt(i);
			
			if (ch == '\0') continue;
			
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
			
			if (KeyEvent.VK_END == keyCode) {
				throw new RuntimeException("Key code not found for character '" + ch + "'");
			}
			
			bot.keyPress(keyCode);
			bot.keyRelease(keyCode);
			
			if (i < messageLen-1) bot.delay(50);
		}
	}
}
