package view.frame.panel.drivepanel.treecombobox;

import javax.swing.JComboBox;

import engine.drive.Drive;
import view.View;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive>{
	
	public TreeComboBox() {
		this.addActionListener(new OnChange(this));
		
		View.engine.addEngineListener(new OnDriveUpdate(this));
	}
	
}
