package engine.actions.delete;

import java.util.ArrayList;

import engine.actions.Actions;
import engine.drives.drive.file.File;
import engine.drives.drive.vault.index.Index;

public class Delete {
	
	private Actions actions;
	
	public Delete(Actions actions) {
		this.actions = actions;
	}
	
	public void file(File file) throws Exception {
		boolean inside_vault = file.drive().utilities().vaults().is_secure(file);
		
		if(file.type().equals("file") && inside_vault)
			safe_file(file);
		else if(file.type().equals("folder") && inside_vault)
			safe_folder(file);
		else if(file.type().equals("file"))
			normal_file(file);
		else if(file.type().equals("folder"))
			normal_folder(file);
	}
	
	private void normal_file(File file) throws Exception {
		file.drive().plugin().deleteFile(file.path());
	}
	
	private void normal_folder(File folder) throws Exception {
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file);
		
		folder.drive().plugin().deleteFolder(folder.path());
	}
	
	private void safe_file(File file) throws Exception {
		Index index = new Index(file.drive().vault(), file.parent());
		
		if(index.file() != null)
			index.remove(file);
		
		file.drive().plugin().deleteFile(file.path());
	}
	
	private void safe_folder(File folder) throws Exception {
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file);

		safe_file(folder);
	}

}
