package view.stringformat;

import java.nio.charset.StandardCharsets;

public class StringFormat {
	
	public String text; 
	
	public StringFormat(String text) {
		this.text = new String(text.getBytes(), StandardCharsets.UTF_8);
	}

	public String toString() {
		return this.text;
	}
}
