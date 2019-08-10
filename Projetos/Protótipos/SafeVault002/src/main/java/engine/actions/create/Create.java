package engine.actions.create;

import java.util.ArrayList;

import engine.drives.drive.file.File;
import engine.drives.drive.vault.index.Index;

public class Create {
	
	public Create() {
		
	}
	
	public File folder(File folder, String name) throws Exception {
		if(!folder.type().equals("folder"))
			return null;
		
		if(folder.drive().utilities().vaults().is_secure(folder))
			return secure_folder(folder, name);
		else
			return normal_folder(folder, name);
	}
	
	private File normal_folder(File folder, String name) throws Exception {
		String path = folder.drive().utilities().strings().concatpath(folder.path(), name);
		
		folder.drive().plugin().createFolder(path);
		
		return new File(folder.drive(), path, "folder");
	}
	
	private File secure_folder(File folder, String name) throws Exception {
		Index parent;
		
		if(folder.equal_to(folder.drive().vault().folder()))
			parent = folder.drive().vault().index();
		else
			parent = new Index(folder.drive().vault(), folder);
		
		String filename = folder.drive().utilities().indexes().validname(parent.read());
		String path = folder.drive().utilities().strings().concatpath(folder.drive().vault().folder().path(), filename);
		
		File new_folder = new File(folder.drive(), path, "folder");
		new_folder.drive().plugin().createFile(path);
		new_folder.set_name(name);
		
		Index index = new Index(folder.drive().vault(), new_folder);
		index.write(new ArrayList<File>());
		
		parent.add(new_folder);
		
		return new_folder;			
	}
}
