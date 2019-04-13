package view.frame.panel.buttonspanel.copy;

import javax.swing.JButton;

import view.frame.panel.drivepanel.DrivePanel;

@SuppressWarnings("serial")
public class Copy extends JButton {
	
	private DrivePanel fromDrivePanel;
	private DrivePanel toDrivePanel;
	
	public Copy(String text, DrivePanel fromDrivePanel, DrivePanel toDrivePanel) {
		this.setText(text);
		this.fromDrivePanel = fromDrivePanel;
		this.toDrivePanel = toDrivePanel;
		
		this.addActionListener(new OnClick(this));
	}
	
	public DrivePanel fromDrivePanel() {
		return fromDrivePanel;
	}
	
	public DrivePanel toDrivePanel() {
		return toDrivePanel;
	}

}
