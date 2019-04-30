package engine;

import java.util.ArrayList;

import engine.drive.DriveList;
import engine.drive.file.File;
import engine.drive.file.action.Copy;
import engine.drive.file.action.Delete;
import engine.drive.file.action.List;
import engine.drive.file.action.Move;

public class Engine extends EngineUpdatable {
	
	public DriveList driverList = new DriveList(this);
	
	public Engine() {
	}
	
	// Eu preciso fazer esses métodos estáticos?
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
	}
	
	public static void move(File file, File toFolder) throws Exception {
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file"))
			Move.moveFile(file, toFolder);
		else if(file.getType().equals("folder"))
			Move.moveFolder(file, toFolder);
	}
	
	public static void delete(File file, boolean recursive) throws Exception {
		if(file.getType().equals("file"))
			Delete.deleteFile(file);
		else if(file.getType().equals("folder") && recursive == false)
			Delete.deleteFolder(file);
		else if(file.getType().equals("folder") && recursive == true)
			Delete.deleteFolderRecursive(file);
	}
	
}
