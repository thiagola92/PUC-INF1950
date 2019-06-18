package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import engine.file.drive.Drive;
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
		if(engineUpdate == UpdateOptions.FILE_UPDATE)
			return;
		
		Drive driveSelected = (Drive)treeCombobox.getSelectedItem();
		
		treeCombobox.removeAllItems();
		
		View.driverList.getDrives().forEach(driver -> treeCombobox.addItem(driver));
		
		for(int i = 0; i < treeCombobox.getItemCount(); i++) {
			Drive drive = treeCombobox.getItemAt(i);
			if(drive == driveSelected)
				treeCombobox.setSelectedIndex(i);
		}
	}

}
