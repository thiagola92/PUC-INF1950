package view.frame.panel.drivepanel.treecombobox;

import engine.EngineListener;
import view.View;

public class OnDriveUpdate implements EngineListener {
	
	private TreeComboBox treeCombobox;
	
	public OnDriveUpdate(TreeComboBox treeCombobox) {
		this.treeCombobox = treeCombobox;
	}

	@Override
	public void engineUpdated() {
		treeCombobox.removeAllItems();
		
		View.engine.driverList.getDrives().forEach(driver -> treeCombobox.addItem(driver));
	}

}
