package view.frames.driveframe.panel.drivepanel.treescrollpane.tree.returnfolder;

import engine.file.File;

public class ReturnFolder extends File {

	public ReturnFolder(File rootFolder, String path) {
		super(rootFolder.getDrive(), path, rootFolder.getType());
	}
	
	public String toString() {
		return "..";
	}

}
