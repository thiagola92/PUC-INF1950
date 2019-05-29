package engine.file.vault;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engine.file.File;

public class Vault {
	
	public static String vaultName = "SafeVault";
	
	public static boolean isInsideVault(File file) {		
		if(file.getPath().startsWith("." + vaultName))
			return true;
		
		if(file.getPath().contains("/." + vaultName))
			return true;
		
		return false;
	}
	
	public static File getVault(File file) {
		Pattern pattern = Pattern.compile("(.*" + java.io.File.separator + java.io.File.separator + "?\\." + vaultName + ").*");
		Matcher matcher = pattern.matcher(file.getPath());
		
		if(matcher.find() == false)
			return null;
		
		String vaultPath = matcher.group();
		File vault = new File(file.getDrive(), vaultPath, "folder");
		
		return vault;
	}

}
