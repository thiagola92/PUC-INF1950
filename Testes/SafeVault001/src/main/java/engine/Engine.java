package engine;

import java.util.ArrayList;

import engine.drive.DriveList;
import engine.file.File;
import engine.file.Utility;
import engine.file.action.Copy;
import engine.file.action.Create;
import engine.file.action.Delete;
import engine.file.action.List;
import engine.file.action.Move;
import engine.update.Update;
import engine.update.UpdateOptions;

public class Engine {
	
	public static DriveList driverList = new DriveList();
	public static Update update = new Update();
	
	public static void createFolder(File folder, String newFolderName) throws Exception {
		if(folder.getType().equals("folder") == false)
			return;
		
		if(Utility.isInsideSafeVault(folder) == false)
			Create.createFolder(folder, newFolderName);
		else
			Create.createSecurityFolder(folder, newFolderName);
		
		update.updateListeners(UpdateOptions.FILE_UPDATE);
	}
	
	public static ArrayList<File> listFolder(File folder) throws Exception {
		if(folder.getType().equals("folder"))
			return List.listFolder(folder);
		
		return null;
	}
	
	public static void copy(File file, File toFolder) throws Exception {
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file"))
			Copy.copyFile(file, toFolder);
		else if(file.getType().equals("folder")) 
			Copy.copyFolder(file, toFolder);
		
		update.updateListeners(UpdateOptions.FILE_UPDATE);
	}
	
	public static void move(File file, File toFolder) throws Exception {		
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file"))
			Move.moveFile(file, toFolder);
		else if(file.getType().equals("folder"))
			Move.moveFolder(file, toFolder);
		
		update.updateListeners(UpdateOptions.FILE_UPDATE);
	}
	
	public static void delete(File file, boolean recursive) throws Exception {
		if(file.getType().equals("file"))
			Delete.deleteFile(file);
		else if(file.getType().equals("folder") && recursive == false)
			Delete.deleteFolder(file);
		else if(file.getType().equals("folder") && recursive == true)
			Delete.deleteFolderRecursive(file);
		
		update.updateListeners(UpdateOptions.FILE_UPDATE);
	}
	
}
