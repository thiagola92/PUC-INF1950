package view.frame.panel.driverpanel.treecombobox;

import javax.swing.JComboBox;

import engine.EngineListener;
import engine.driver.Drive;
import view.View;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive> implements EngineListener {
	
	public TreeComboBox() {
		View.engine.addEngineListener(this);
		View.engine.driverList.getDrives().forEach(driver -> this.addItem(driver));
	}
	
	public void engineUpdated() {
		this.removeAllItems();

		View.engine.driverList.getDrives().forEach(driver -> this.addItem(driver));
	}
	
}
