package view.frame.panel.drivepanel.delete;

import javax.swing.JButton;

import view.frame.panel.drivepanel.DrivePanel;
import view.frame.panel.drivepanel.delete.OnClick;

@SuppressWarnings("serial")
public class Delete extends JButton {
	
	private DrivePanel fromDrivePanel;
	
	public Delete(DrivePanel fromDrivePanel) {
		this.setText("X");
		this.fromDrivePanel = fromDrivePanel;
		
		this.addActionListener(new OnClick(this));
	}
	
	public DrivePanel fromDrivePanel() {
		return fromDrivePanel;
	}
}
