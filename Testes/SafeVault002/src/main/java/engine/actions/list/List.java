package engine.actions.list;

import java.util.ArrayList;

import engine.drives.drive.file.File;
import engine.drives.drive.vault.index.Index;

public class List {
	
	public List() {
		
	}
	
	public ArrayList<File> folder(File folder) throws Exception {
		if(folder.drive().utilities().vaults().is_secure(folder))
			return safe_folder(folder);
		else
			return normal_folder(folder);
	}
	
	public ArrayList<File> normal_folder(File folder) throws Exception {
		if(!folder.type().equals("folder"))
			return null;
		
		ArrayList<File> files = new ArrayList<File>();
		
		folder.drive().plugin().listFolder(folder.path()).forEach((file) -> {
			String path = folder.drive().utilities().strings().concatpath(folder.path(), file[0]);
			File new_file = new File(folder.drive(), path, file[1]);
			new_file.set_parent(folder);
			
			files.add(new_file);
		});
		
		return files;
	}
	
	private ArrayList<File> safe_folder(File folder) throws Exception {
		if(!folder.type().equals("folder"))
			return null;
		
		if(folder.equal_to(folder.drive().vault().folder()))
			return folder.drive().vault().index().read();
		
		Index index = new Index(folder.drive().vault(), folder);
		
		return index.read();
	}

}
