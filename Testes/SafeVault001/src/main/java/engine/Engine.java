package engine;

import java.util.ArrayList;

import engine.file.File;
import engine.file.action.Cipher;
import engine.file.action.Copy;
import engine.file.action.Create;
import engine.file.action.Decipher;
import engine.file.action.Delete;
import engine.file.action.List;
import engine.file.action.Move;
import engine.file.vault.Vault;

public class Engine {
	
	public static void createFolder(File folder, String newFolderName) throws Exception {
		if(folder.getType().equals("folder") == false)
			return;
		
		if(Vault.isInsideVault(folder))
			Create.createSafeFolder(folder, newFolderName);
		else
			Create.createFolder(folder, newFolderName);
	}
	
	public static ArrayList<File> listFolder(File folder) throws Exception {
		if(folder.getType().equals("folder") == false)
			return null;
		
		if(Vault.isInsideVault(folder))
			return List.listSafeFolder(folder);
		else
			return List.listFolder(folder);
	}
	
	public static void copy(File file, File toFolder) throws Exception {
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file"))
			Copy.copyFile(file, toFolder);
		else if(file.getType().equals("folder")) 
			Copy.copyFolder(file, toFolder);
	}
	
	public static void move(File file, File toFolder) throws Exception {		
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file"))
			Move.moveFile(file, toFolder);
		else if(file.getType().equals("folder"))
			Move.moveFolder(file, toFolder);
	}
	
	public static void delete(File file) throws Exception {
		if(Vault.isInsideVault(file) && file.getType().equals("file"))
			Delete.deleteSafeFile(file);
		else if(Vault.isInsideVault(file) && file.getType().equals("folder"))
			Delete.deleteSafeFolder(file);
		else if(file.getType().equals("file"))
			Delete.deleteFile(file);
		else if(file.getType().equals("folder"))
			Delete.deleteFolder(file);
	}
	
	public static void cipher(File file) throws Exception {
		if(file.getType().equals("folder"))
			Cipher.cipherFolder(file);
		else if(file.getType().equals("file"))
			Cipher.cipherFile(file);
	}
	
	public static void decipher(File file) throws Exception {
		if(file.getType().equals("folder"))
			Decipher.decipherFolder(file);
		else if(file.getType().equals("file"))
			Decipher.decipherFile(file);
	}
	
}
