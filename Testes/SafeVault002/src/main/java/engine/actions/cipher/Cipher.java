package engine.actions.cipher;

import java.util.ArrayList;

import engine.actions.Actions;
import engine.drives.drive.file.File;

public class Cipher {
	
	private Actions actions;
	
	public Cipher(Actions actions) {
		this.actions = actions;
	}
	
	public void file(File file) throws Exception {
		if(file.drive().utilities().vaults().is_secure(file))
			return;
		
		if(file.type().equals("file"))
			normal_file(file);
		else if(file.type().equals("folder"))
			normal_folder(file);
	}
	
	private void normal_file(File file) throws Exception {
		byte[] content = file.drive().plugin().readFile(file.path());
		byte[] container = file.drive().utilities().cryptography().encrypt().content(content, file.drive().vault().privatekey(), file.drive().vault().publickey());
		file.drive().plugin().writeFile(file.path(), container);
	}
	
	private void normal_folder(File folder) throws Exception {
		ArrayList<File> files = actions.list().folder(folder);
		
		for(File file : files)
			file(file);
	}

}
