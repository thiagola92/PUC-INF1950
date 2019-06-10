package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import view.View;
import view.update.UpdateListener;
import view.update.UpdateOptions;

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
		View.driverList.getDrives().forEach(driver -> treeCombobox.addItem(driver));
	}

}
