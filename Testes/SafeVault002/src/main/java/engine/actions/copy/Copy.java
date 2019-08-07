package engine.actions.copy;

import java.util.ArrayList;

import engine.actions.Actions;
import engine.drives.drive.file.File;
import engine.drives.drive.vault.index.Index;

public class Copy {
	
	private Actions actions;
	
	public Copy(Actions actions) {
		this.actions = actions;
	}
	
	public void file(File file, File folder) throws Exception {
		if(!folder.type().equals("folder"))
			return;
		
		boolean file_inside_vault = file.drive().utilities().vaults().is_secure(file);
		boolean folder_inside_vault = folder.drive().utilities().vaults().is_secure(folder);
		
		if(file.type().equals("file") && file_inside_vault && folder_inside_vault)
			safe_file_to_safe_folder(file, folder);
		else if(file.type().equals("folder") && file_inside_vault && folder_inside_vault)
			safe_folder_to_safe_folder(file, folder);
		else if(file.type().equals("file") && !file_inside_vault && folder_inside_vault)
			normal_file_to_safe_folder(file, folder); //
		else if(file.type().equals("folder") && !file_inside_vault && folder_inside_vault)
			normal_folder_to_safe_folder(file, folder); //
		else if(file.type().equals("file") && file_inside_vault && !folder_inside_vault)
			safe_file_to_normal_folder(file, folder);
		else if(file.type().equals("folder") && file_inside_vault && !folder_inside_vault)
			safe_folder_to_normal_folder(file, folder);
		else if(file.type().equals("file"))
			normal_file_to_normal_folder(file, folder);
		else if(file.type().equals("folder"))
			normal_folder_to_normal_folder(file, folder);
	}
	
	private void normal_file_to_normal_folder(File file, File folder) throws Exception {
		String path = folder.drive().utilities().strings().concatpath(folder.path(), file.name());
		folder.drive().plugin().createFile(path);
		
		byte[] content = file.drive().plugin().readFile(file.path());
		folder.drive().plugin().writeFile(path, content);
	}
	
	private void normal_folder_to_normal_folder(File folder, File tofolder) throws Exception {
		File new_folder = actions.create().folder(tofolder, folder.name());
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file, new_folder);
	}
	
	private void safe_file_to_normal_folder(File file, File folder) throws Exception {
		String path = folder.drive().utilities().strings().concatpath(folder.path(), file.name());
		folder.drive().plugin().createFile(path);
		
		byte[] container = file.drive().plugin().readFile(file.path());
		byte[] content = file.drive().utilities().cryptography().decrypt().container(container,
																					file.drive().vault().privatekey(),
																					file.drive().vault().publickey());
		folder.drive().plugin().writeFile(path, content);
	}
	
	private void safe_folder_to_normal_folder(File folder, File tofolder) throws Exception {
		File new_folder = actions.create().folder(tofolder, folder.name());
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file, new_folder);
	}
	
	private void normal_file_to_safe_folder(File file, File folder) throws Exception {
		Index index;
		
		if(folder.equal_to(folder.drive().vault().folder()))
			index = folder.drive().vault().index();
		else
			index = new Index(folder.drive().vault(), folder);
		
		String filename = folder.drive().utilities().indexes().validname(index.read());
		String path = folder.drive().utilities().strings().concatpath(folder.drive().vault().folder().path(), filename);
		
		File new_file = new File(folder.drive(), path, "file");
		new_file.drive().plugin().createFile(path);
		new_file.set_name(file.name());

		byte[] content = file.drive().plugin().readFile(file.path());
		byte[] new_container = folder.drive().utilities().cryptography().encrypt().content(content,
																					folder.drive().vault().privatekey(),
																					folder.drive().vault().publickey());
		
		new_file.drive().plugin().writeFile(new_file.path(), new_container);
		index.add(new_file);
	}
	
	private void normal_folder_to_safe_folder(File folder, File tofolder) throws Exception {
		File new_folder = actions.create().folder(tofolder, folder.name());
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file, new_folder);
	}
	
	private void safe_file_to_safe_folder(File file, File folder) throws Exception {
		Index index;
		
		if(folder.equal_to(folder.drive().vault().folder()))
			index = folder.drive().vault().index();
		else
			index = new Index(folder.drive().vault(), folder);
		
		String filename = folder.drive().utilities().indexes().validname(index.read());
		String path = folder.drive().utilities().strings().concatpath(folder.drive().vault().folder().path(), filename);
		
		File new_file = new File(folder.drive(), path, "file");
		new_file.drive().plugin().createFile(path);
		new_file.set_name(file.name());

		byte[] container = file.drive().plugin().readFile(file.path());
		byte[] content = file.drive().utilities().cryptography().decrypt().container(container,
																					file.drive().vault().privatekey(),
																					file.drive().vault().publickey());
		byte[] new_container = folder.drive().utilities().cryptography().encrypt().content(content,
																					folder.drive().vault().privatekey(),
																					folder.drive().vault().publickey());
		
		new_file.drive().plugin().writeFile(new_file.path(), new_container);
		index.add(new_file);
	}
	
	private void safe_folder_to_safe_folder(File folder, File tofolder) throws Exception {
		File new_folder = actions.create().folder(tofolder, folder.name());
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file, new_folder);
	}

}
