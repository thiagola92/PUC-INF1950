package view.frame.panel.drivepanel.treecombobox;

import javax.swing.JComboBox;

import engine.EngineAction;
import engine.EngineListener;
import engine.driver.Drive;
import view.View;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive> implements EngineListener {
	
	public TreeComboBox() {
		this.addActionListener(new OnChange(this));
		
		View.engine.addEngineListener(this);
		View.engine.driverList.getDrives().forEach(driver -> this.addItem(driver));
	}
	
	public void engineUpdated(EngineAction action) {
		this.removeAllItems();
		
		View.engine.driverList.getDrives().forEach(driver -> this.addItem(driver));
	}
	
}
