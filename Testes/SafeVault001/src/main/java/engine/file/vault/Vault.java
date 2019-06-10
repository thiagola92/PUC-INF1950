package engine.file.vault;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engine.file.File;
import engine.file.Utility;
import engine.file.action.Create;
import engine.file.vault.cryptography.Encrypt;

public class Vault {

	private static String vaultName = ".SafeVault";
	private static String vaultNameRegex = "\\" + vaultName;
	private static String slashRegex = java.io.File.separator + java.io.File.separator;
	
	public static boolean isInsideVault(File file) {
		// Vault is the start path
		if(file.getPath().startsWith(vaultName))
			return true;
		
		// Vault is in the path
		if(file.getPath().contains(java.io.File.separator + vaultName))
			return true;
		
		return false;
	}
	
	public static File getVault(ArrayList<File> files) throws Exception {		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals(vaultName))
				return files.get(i);
		
		return null;
	}
	
	/**
	 * Get the vault folder from this file
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
	
	public static File createSafeVault(File file) throws Exception {
		File vault = Create.createFolder(file, vaultName);
		
		String indexPath = Utility.concatPath(vault.getPath(), "index");
		file.getDrive().getPlugin().createFile(indexPath);
		
		byte[] container = Encrypt.getEncryptedFile("".getBytes(), file.getDrive().getPrivateKey(), file.getDrive().getPublicKey());
		file.getDrive().getPlugin().writeFile(indexPath, container);
		
		return vault;
	}

}
