package view.frame.menubar.drive.removedrive.drive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.Engine;

public class OnClick implements ActionListener {
	
	private Drive drive;
	
	public OnClick(Drive drive) {
		this.drive = drive;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Engine.driverList.removeDrive(drive.getText());
	}

}
