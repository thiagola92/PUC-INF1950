package engine.actions.move;

import engine.actions.Actions;
import engine.drives.drive.file.File;

public class Move {
	
	private Actions actions;
	
	public Move(Actions actions) {
		this.actions = actions;
	}
	
	public void file(File file, File folder) {
		if(!folder.type().equals("folder"))
			return;
		
		if(file.equal_to(folder))
			return;
		
		copy_delete(file, folder);
	}
	
	private void copy_delete(File file, File folder) {
		try {
			actions.copy().file(file, folder);
			actions.delete().file(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
