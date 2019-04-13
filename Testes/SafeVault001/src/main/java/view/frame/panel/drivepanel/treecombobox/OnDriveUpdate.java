package view.frame.panel.drivepanel.treecombobox;

import engine.EngineAction;
import engine.EngineListener;
import view.View;

public class OnDriveUpdate implements EngineListener {
	
	private TreeComboBox treeCombobox;
	
	public OnDriveUpdate(TreeComboBox treeCombobox) {
		this.treeCombobox = treeCombobox;
	}

	@Override
	public void engineUpdated(EngineAction action) {
		treeCombobox.removeAllItems();
		
		View.engine.driverList.getDrives().forEach(driver -> treeCombobox.addItem(driver));
	}

}
