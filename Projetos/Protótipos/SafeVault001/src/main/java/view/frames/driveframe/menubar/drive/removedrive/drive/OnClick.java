package view.frames.driveframe.menubar.drive.removedrive.drive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;

public class OnClick implements ActionListener {
	
	private Drive drive;
	
	public OnClick(Drive drive) {
		this.drive = drive;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		View.driverList.removeDrive(drive.getText());
	}

}
