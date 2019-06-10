package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import javax.swing.JComboBox;

import engine.file.drive.Drive;
import view.View;
import view.frames.driveframe.panel.drivepanel.toppanel.TopPanel;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive>{
	
	public TopPanel topPanel;
	
	public TreeComboBox(TopPanel topPanel) {
		this.topPanel = topPanel;
		
		this.addActionListener(new OnChange(this));
		
		View.update.addUpdateListener(new OnDriveUpdate(this));
	}
	
}
