package engine.file.vault;

import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
import engine.file.Utility;
import engine.file.action.Create;
import engine.file.vault.cryptography.Encrypt;

public class Vault {

	private static String vaultName = ".SafeVault";
	private static String vaultNameRegex = "\\" + vaultName;
	
	public static boolean isInsideVault(File file) {
		// Vault is the start path
		if(file.getPath().startsWith(vaultName))
			return true;
		
		// Vault is in the path
		if(file.getPath().contains(java.io.File.separator + vaultName))
			return true;
		
		return false;
	}
	
	public static boolean existVault(File file) throws Exception {
		ArrayList<File> files = Engine.listFolder(file);
		
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).getName().equals(vaultName))
				return true;
		
		return false;
	}
	
	public static File getVault(File file) {
		String vaultPath = Utility.concatPath(file.getDrive().getStartPath(), vaultName);
		
		return new File(file.getDrive(), vaultPath, "folder");
	}
	
	public static String pathInsideVault(File file) {
		String pathInsideVault = file.getPath().replaceFirst(".*" + vaultNameRegex, "");
		
		if(pathInsideVault.isEmpty())
			return java.io.File.separator;
		
		return pathInsideVault;
	}
	
	public static File createSafeVault(File file) throws Exception {
		byte[] container = Encrypt.getEncryptedFile("".getBytes(), file.getDrive().getPrivateKey(), file.getDrive().getPublicKey());
		
		File vault = Create.createFolder(file, vaultName);
		
		String indexPath = Utility.concatPath(vault.getPath(), "index");
		file.getDrive().getPlugin().createFile(indexPath);
		file.getDrive().getPlugin().writeFile(indexPath, container);
		
		return vault;
	}

}
