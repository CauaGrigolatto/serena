package br.com.cauag.serena.core.syntax;

import java.awt.event.KeyEvent;

public enum Syntax {
	EXECUTE("EXECUTE"),
	MOVE_MOUSE("MOVE_MOUSE"),
	LEFT_CLICK("LEFT_CLICK"),
	RIGHT_CLICK("RIGHT_CLICK"),
	WAIT_SECONDS("WAIT_SECONDS"),
	WAIT_MILLIS("WAIT_MILLIS"),
	TYPE("TYPE"),
	PRESS("PRESS"),
	DISPLAY("DISPLAY"),
	COPY("COPY"),
	PASTE("PASTE"),
	REPEAT("REPEAT"), 
	END_REPEAT("END_REPEAT"),
	BLOCK("BLOCK"), 
	END_BLOCK("END_BLOCK"), 
	CALL("CALL"),
	SCHEDULE("SCHEDULE"), 
	END_SCHEDULE("END_SCHEDULE"),
	INCLUDE("INCLUDE"),
	SET("SET"),
	ASSIGN("ASSIGN");
	
	private String syntax;
	
	private Syntax(String syntax) {
		this.syntax = syntax;
	}
	
	public boolean sameAs(String s) {
		return syntax.equals(s);
	}
	
	public enum SpecialKey {
		ENTER(KeyEvent.VK_ENTER),
		SPACE(KeyEvent.VK_SPACE),
		BACKSPACE(KeyEvent.VK_BACK_SPACE),
		TAB(KeyEvent.VK_TAB);
		
		private int keyCode;
		
		private SpecialKey(int keyCode) {
			this.keyCode = keyCode;
		}
		
		public int getKeyCode() {
			return keyCode;
		}
	}
}
