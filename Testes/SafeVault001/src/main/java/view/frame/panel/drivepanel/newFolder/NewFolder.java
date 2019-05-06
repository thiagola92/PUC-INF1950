package view.frame.panel.drivepanel.newFolder;

import javax.swing.JButton;

import view.frame.panel.drivepanel.DrivePanel;

@SuppressWarnings("serial")
public class NewFolder extends JButton {
	
	private DrivePanel toDrivePanel;
	
	public NewFolder(DrivePanel toDrivePanel) {
		this.setText("+");
		this.toDrivePanel = toDrivePanel;
		
		this.addActionListener(new OnClick(this));
	}
	
	public DrivePanel toDrivePanel() {
		return toDrivePanel;
	}

}
