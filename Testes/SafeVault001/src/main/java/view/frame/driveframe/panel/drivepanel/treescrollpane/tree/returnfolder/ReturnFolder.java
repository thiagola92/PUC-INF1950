package view.frame.driveframe.panel.drivepanel.treescrollpane.tree.returnfolder;

import engine.file.File;
import engine.file.drive.Drive;

public class ReturnFolder extends File {

	public ReturnFolder(Drive drive, String path, String type) {
		super(drive, path, type);
	}
	
	public String toString() {
		return "..";
	}

}
