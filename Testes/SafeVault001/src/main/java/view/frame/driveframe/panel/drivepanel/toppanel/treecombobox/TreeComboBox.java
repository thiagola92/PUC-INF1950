package view.frame.driveframe.panel.drivepanel.toppanel.treecombobox;

import javax.swing.JComboBox;

import engine.file.drive.Drive;
import view.View;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive>{
	
	public TreeComboBox() {
		this.addActionListener(new OnChange(this));
		
		View.update.addUpdateListener(new OnDriveUpdate(this));
	}
	
}
