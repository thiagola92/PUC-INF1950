package engine.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	
	// "src" + "main" = "src/main"
	// "" + "main" = "main" 
	public static String concatPath(String parentPath, String childName) {
		if(parentPath.isEmpty())
			return childName;
		else
			return parentPath + java.io.File.separator + childName;
	}
	
	public static boolean isInsideSafeVault(File file) {		
		if(file.getPath().startsWith(".SafeVault"))
			return true;
		
		if(file.getPath().contains("/.SafeVault"))
			return true;
		
		return false;
	}
	
	public static String getSafeVaultPath(File file) {
		Pattern pattern = Pattern.compile("(.*" + java.io.File.separator + java.io.File.separator + "?\\.SafeVault).*");
		Matcher matcher = pattern.matcher(file.getPath());
		
		if(matcher.find())
			return matcher.group();
		
		return null;
	}

}
