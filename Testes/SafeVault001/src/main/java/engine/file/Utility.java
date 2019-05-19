package engine.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	
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
		
		if(matcher.find()) {
			System.out.println("FOUND");
			return matcher.group();
		}
		
		return null;
	}

}
