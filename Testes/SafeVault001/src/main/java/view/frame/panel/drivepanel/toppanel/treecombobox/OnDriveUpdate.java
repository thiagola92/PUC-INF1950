package view.frame.panel.drivepanel.toppanel.treecombobox;

import engine.Engine;
import engine.update.UpdateListener;
import engine.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	private TreeComboBox treeCombobox;
	
	public OnDriveUpdate(TreeComboBox treeCombobox) {
		this.treeCombobox = treeCombobox;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate != UpdateOptions.DRIVE_UPDATE)
			return;
		
		treeCombobox.removeAllItems();
		Engine.driverList.getDrives().forEach(driver -> treeCombobox.addItem(driver));
	}

}
