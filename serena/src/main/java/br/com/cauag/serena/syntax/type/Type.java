package br.com.cauag.serena.syntax.type;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.QuotedParameter;
import br.com.cauag.serena.syntax.ExecutableAndParametersReceiver;

public class Type extends ExecutableAndParametersReceiver {
	
	private Map<Character, SpecialChar> specialChars;
	
	public Type() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		
		this.specialChars = new HashMap<Character, SpecialChar>();
		this.specialChars.put('?', new QuestionMark());
		this.specialChars.put('!', new ExclamationMark());
		this.specialChars.put('#', new Hashtag());
		this.specialChars.put('$', new Dollar());
		this.specialChars.put('%', new Mod());
		
		this.specialChars.put('&', new Ampersand());
		this.specialChars.put('*', new Asterisk());
		this.specialChars.put('(', new OpenParenthesis());
		this.specialChars.put(')', new CloseParenthesis());
		this.specialChars.put('_', new Underline());
		
		this.specialChars.put('+', new Plus());
		this.specialChars.put('{', new OpenBraces());
		this.specialChars.put('}', new CloseBraces());
		this.specialChars.put(':', new Colon());
		this.specialChars.put('|', new Pipe());
		
		this.specialChars.put('>', new GreaterThan());
		this.specialChars.put('<', new LessThan());
		this.specialChars.put('@', new At());
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		
		String message = QuotedParameter.valueOf(complement);
		message = applyParametersAndVariables(message, core);
		int messageLen = message.length();
		
		for (int i = 0; i < messageLen; i++) {
			char ch = message.charAt(i);
			
			if (ch == '\0') continue;
			
			SpecialChar specialChar = specialChars.get(ch);
			
			if (specialChar != null) {
				specialChar.press(core);
			}
			else {
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
				
				boolean isUpper = Character.isUpperCase(ch);
				
				if (isUpper) {
					core.bot.keyPress(KeyEvent.VK_SHIFT);
				}
				
				try {
					core.bot.keyPress(keyCode);
					core.bot.keyRelease(keyCode);				
				}
				catch(IllegalArgumentException e) {
					throw new IllegalArgumentException(e.getMessage() + ": " + ch + ".");
				}

				if (isUpper) {
					core.bot.keyRelease(KeyEvent.VK_SHIFT);
				}
			}
				
			if (i < messageLen-1) Thread.sleep(Duration.ofMillis(100));
		}
		
		return core.index;
	}
}
