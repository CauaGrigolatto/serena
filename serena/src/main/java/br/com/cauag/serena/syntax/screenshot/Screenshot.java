package br.com.cauag.serena.syntax.screenshot;

import java.awt.Rectangle;
import java.awt.Toolkit;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.Executable;

public class Screenshot extends Executable {
	
	public Screenshot() {
		super();
		addSuccessor("TO", new ScreenshotTo());
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		core.bot.createScreenCapture(
			new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()
			)
		);
		
		return core.index;
	}
	
}
