package br.com.cauag.serena.syntax.type;

import br.com.cauag.serena.core.Core;

public interface SpecialChar {
	default void press(Core core) {
		keyPress(core);
		keyRelease(core);
	}
	
	void keyPress(Core core);
	void keyRelease(Core core);
}
