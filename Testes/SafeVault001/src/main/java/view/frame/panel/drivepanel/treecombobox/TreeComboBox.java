package view.frame.panel.drivepanel.treecombobox;

import javax.swing.JComboBox;

import engine.Engine;
import engine.file.drive.Drive;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive>{
	
	public TreeComboBox() {
		this.addActionListener(new OnChange(this));
		
		Engine.update.addUpdateListener(new OnDriveUpdate(this));
	}
	
}
