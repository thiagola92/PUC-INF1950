package engine;

import java.util.ArrayList;

import engine.drive.DriveList;
import engine.drive.file.File;

public class Engine extends EngineUpdatable {
	
	public DriveList driverList = new DriveList(this);
	
	public Engine() {
	}
	
	// "src" + "main" = "src/main"
	// "" + "main" = "main" 
	private String concatPath(String parentPath, String childName) {
		if(parentPath.isEmpty())
			return childName;
		else
			return parentPath + java.io.File.separator + childName;
	}
	
	public ArrayList<File> listFolder(File folder) throws Exception {
		ArrayList<File> files = new ArrayList<File>();
		
		folder.getDrive().getPlugin().listFolder(folder.getPath()).forEach(file -> {
			String filePath = concatPath(folder.getPath(), file[0]);
			
			files.add(new File(folder.getDrive(), filePath, file[1]));
		});
		
		return files;
	}
	
	public void copy(File file, File toFolder) throws Exception {
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file")) {
			String filePath = toFolder.getPath() + java.io.File.separator + file.getName();
			toFolder.getDrive().getPlugin().createFile(filePath);

			byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
			toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);
			
			return;
		}
			
		
		if(file.getType().equals("folder")) {
			
		}
		
	}
	
	public void move(File file, File toFolder) throws Exception {
		if(toFolder.getType().equals("file"))
			return;
		
		if(file.getType().equals("file")) {
			String filePath = concatPath(toFolder.getPath(), file.getName());
			toFolder.getDrive().getPlugin().createFile(filePath);

			byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
			toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);
			
			delete(file);
			
			return;
		}
	}
	
	public void delete(File file) throws Exception {
		if(file.getType().equals("file"))
			file.getDrive().getPlugin().deleteFile(file.getPath());
	}
	
}
