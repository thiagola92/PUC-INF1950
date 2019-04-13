package view.frame.panel.buttonspanel.delete;

import javax.swing.JButton;

import view.frame.panel.buttonspanel.delete.OnClick;
import view.frame.panel.drivepanel.DrivePanel;

@SuppressWarnings("serial")
public class Delete extends JButton {
	
	private DrivePanel fromDrivePanel;
	
	public Delete(String text, DrivePanel fromDrivePanel) {
		this.setText(text);
		this.fromDrivePanel = fromDrivePanel;
		
		this.addActionListener(new OnClick(this));
	}
	
	public DrivePanel fromDrivePanel() {
		return fromDrivePanel;
	}
}
