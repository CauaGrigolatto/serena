package br.com.cauag.serena.core.syntax;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Type extends ReservedWord {
	
	public Type() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String message = QuotedParameter.valueOf(complement);
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
				core.bot.keyPress(KeyEvent.VK_SHIFT);
			}
			
			core.bot.keyPress(keyCode);
			core.bot.keyRelease(keyCode);
			
			if (isUpper) {
				core.bot.keyRelease(KeyEvent.VK_SHIFT);
			}
			
			if (i < messageLen-1) core.bot.delay(1);
		}
		
		return core.index;
	}
}
