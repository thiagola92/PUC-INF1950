package view.panel.driverpanel;

import javax.swing.JComboBox;

@SuppressWarnings({ "serial", "rawtypes" })
public class TreeComboBox extends JComboBox {
	
	@SuppressWarnings("unchecked")
	public TreeComboBox() {
		this.addItem("C:\\Windows");
		this.addItem("Google Drive");
		this.addItem("C:\\Users\\ThiagoLA92\\Documents");
	}
}
