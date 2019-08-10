package view.utilities.strings;

import java.nio.charset.StandardCharsets;

public class Strings {
	
	public Strings() {
		
	}
	
	public String utf8(String string) {
		return new String(string.getBytes(), StandardCharsets.UTF_8);
	}

}
