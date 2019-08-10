package view.frames.driveframe.panel.drivepanel.toppanel.settingsbutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.file.drive.Drive;
import view.frames.driveframe.panel.drivepanel.toppanel.treecombobox.TreeComboBox;
import view.frames.settingsframe.SettingsFrame;

public class OnClick implements ActionListener {
	
	private SettingsButton settingsButton;
	
	public OnClick(SettingsButton settingsButton) {
		this.settingsButton = settingsButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TreeComboBox treeComboBox = settingsButton.topPanel.treeComboBox;
		Drive drive = (Drive)treeComboBox.getSelectedItem();
		
		if(drive == null)
			return;
		
		new SettingsFrame(drive);
	}

}
