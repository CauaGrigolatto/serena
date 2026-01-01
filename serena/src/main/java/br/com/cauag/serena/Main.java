package br.com.cauag.serena;

import br.com.cauag.serena.core.Core;

public class Main {
	public static void main(String[] args) throws Exception {
		String executableFile = args[0];
		Core core = new Core(executableFile);
		core.run();
	}
}
