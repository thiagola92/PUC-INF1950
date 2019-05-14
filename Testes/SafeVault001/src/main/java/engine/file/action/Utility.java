package engine.file.action;

public class Utility {
	
	// "src" + "main" = "src/main"
	// "" + "main" = "main" 
	public static String concatPath(String parentPath, String childName) {
		if(parentPath.isEmpty())
			return childName;
		else
			return parentPath + java.io.File.separator + childName;
	}

}
