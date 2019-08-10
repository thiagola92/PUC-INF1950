package engine.drives.drive.utilities.strings;

import java.nio.file.Paths;

public class Strings {
	
	public Strings() {
		
	}
	
	public String concatpath(String parent, String child) {
		String path;
		
		if(parent.isEmpty())
			path = child;
		else
			path = parent + java.io.File.separator + child;
		
		return Paths.get(path).normalize().toString();
	}

}
