package br.com.cauag.serena.syntax.screenshot;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.QuotedParameter;
import br.com.cauag.serena.syntax.ExecutableAndParametersReceiver;

public class ScreenshotTo extends ExecutableAndParametersReceiver {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		try {
			String path = QuotedParameter.valueOf(complement).trim();
			String extension = FilenameUtils.getExtension(path);
			BufferedImage image = core.bot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, extension, new File(path));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return core.index;
	}
}
