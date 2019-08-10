package engine.file.action;

import java.nio.file.Paths;
import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
import engine.file.RandomName;
import engine.file.Utility;
import engine.file.vault.Vault;
import engine.file.vault.cryptography.Decrypt;
import engine.file.vault.cryptography.Encrypt;
import engine.file.vault.index.Index;

public class Copy {
	
	public static void copyFile(File file, File toFolder) throws Exception {
		String filePath = Utility.concatPath(toFolder.getPath(), file.getName());
		toFolder.getDrive().getPlugin().createFile(filePath);

		byte[] fileBytes = getBytes(file);
		
		toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);
	}
	
	public static void copyFolder(File folder, File toFolder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);
		
		File newFolder = Engine.createFolder(toFolder, folder.getName());
		
		for(File file : files)
			Engine.copy(file, newFolder);
	}
	
	public static void copyFileToSafeFolder(File file, File toFolder) throws Exception {
		String randomName = RandomName.createRandomName(toFolder);
		
		File vault = Vault.getVault(toFolder);
		String filePath = Utility.concatPath(vault.getPath(), randomName);
		
		toFolder.getDrive().getPlugin().createFile(filePath);
		
		byte[] fileBytes = getBytes(file);
		fileBytes = Encrypt.getEncryptedFile(fileBytes, toFolder.getDrive().getPrivateKey(), toFolder.getDrive().getPublicKey());
		
		toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);

		String filePathToIndex = Utility.concatPath(toFolder.getPath(), randomName);
		File fileToIndex = new File(toFolder.getDrive(), filePathToIndex, file.getType());
		fileToIndex.setName(file.getName());
		
		Index.getIndex(toFolder).addFile(fileToIndex);
	}
	
	public static void copyFolderToSafeFolder(File folder, File toFolder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);

		File newFolder = Engine.createFolder(toFolder, folder.getName());
		
		for(File file : files)
			Engine.copy(file, newFolder);
	}
	
	public static byte[] getBytes(File file) throws Exception {
		if(Vault.isInsideVault(file) == false)
			return file.getDrive().getPlugin().readFile(file.getPath());
		
		File vault = Vault.getVault(file);
		String fileName = Paths.get(file.getPath()).getFileName().toString();
		String filePath = Utility.concatPath(vault.getPath(), fileName);
		
		byte[] fileBytes = file.getDrive().getPlugin().readFile(filePath);
		fileBytes = Decrypt.getDecryptedFile(fileBytes, file.getDrive().getPrivateKey(), file.getDrive().getPublicKey());
		
		return fileBytes;
	}
}
