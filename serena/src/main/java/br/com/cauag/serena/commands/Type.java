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
		int messageLen = message.length();
		
		for (int i = 0; i < messageLen; i++) {
			char ch = message.charAt(i);
			
			if (ch == '\0') continue;
			
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
			
			if (KeyEvent.VK_END == keyCode) {
				throw new RuntimeException("Key code not found for character '" + ch + "'");
			}
			
			boolean isUpper = Character.isUpperCase(ch);
			
			if (isUpper) {
				bot.keyPress(KeyEvent.VK_SHIFT);
			}
			
			bot.keyPress(keyCode);
			bot.keyRelease(keyCode);
			
			if (isUpper) {
				bot.keyRelease(KeyEvent.VK_SHIFT);
			}
			
			if (i < messageLen-1) bot.delay(1);
		}
	}
}
