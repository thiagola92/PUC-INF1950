package view.driveframe.panel.drivepanel.toppanel.settingsbutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.file.drive.Drive;
import view.View;
import view.driveframe.panel.drivepanel.DrivePanel;
import view.settingsframe.SettingsFrame;

public class OnClick implements ActionListener {
	
	private SettingsButton settingsButton;
	
	public OnClick(SettingsButton settingsButton) {
		this.settingsButton = settingsButton;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.driveFrame.panel.firstDrivePanel.topPanel.settingsButton == settingsButton)
			return View.driveFrame.panel.firstDrivePanel;
		
		return View.driveFrame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Drive drive = (Drive)getDrivePanel().topPanel.treeComboBox.getSelectedItem();
		
		if(drive == null)
			return;
		
		SettingsFrame settingsFrame = new SettingsFrame(drive);
	}

}
