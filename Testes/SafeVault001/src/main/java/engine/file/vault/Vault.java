package engine.file.vault;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engine.file.File;

public class Vault {
	
	private static String vaultName = "SafeVault";
	private static String vaultNameRegex = "\\.SafeVault";
	private static String slashRegex = java.io.File.separator + java.io.File.separator;
	
	public static boolean isInsideVault(File file) {
		// Vault is the start path
		if(file.getPath().startsWith("." + vaultName))
			return true;
		
		// Vault is in the path
		if(file.getPath().contains(java.io.File.separator + "." + vaultName))
			return true;
		
		return false;
	}
	
	/**
	 * Get the vault folder
	 * For example, a file with the path:
	 * /home/thiagola92/Downloads/Exemplo/.SafeVault/XXX3/XXX4
	 * will return a folder to
	 * /home/thiagola92/Downloads/Exemplo/.SafeVault
	 * This is where the main index will be
	 * @param file
	 * @return
	 */
	public static File getVault(File file) {
		Pattern pattern = Pattern.compile("(.*" + slashRegex + "?" + vaultNameRegex + ").*");
		Matcher matcher = pattern.matcher(file.getPath());
		
		if(matcher.find() == false)
			return null;
		
		String vaultPath = matcher.group(1);
		File vault = new File(file.getDrive(), vaultPath, "folder");
		
		return vault;
	}
	
	/**
	 * For the index doesn't matter anything outside the vault
	 * So this functions return the path inside the vault
	 * For example, a file with the path:
	 * /home/thiagola92/Downloads/Exemplo/.SafeVault/XXX3/XXX4
	 * Will return a string with the path
	 * /XXX3/XXX4
	 * Using this path you can search the index the true name of this file
	 * @param file
	 * @return
	 */
	public static String pathInsideVault(File file) {
		String[] parts = file.getPath().split(slashRegex + "?" + vaultNameRegex);
		
		if(parts.length < 2)
			return java.io.File.separator;
		
		return parts[1];
	}

}
