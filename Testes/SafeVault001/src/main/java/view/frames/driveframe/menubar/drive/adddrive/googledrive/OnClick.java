package view.frames.driveframe.menubar.drive.adddrive.googledrive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import engine.file.drive.Drive;
import view.View;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String driveName = JOptionPane.showInputDialog("Drive name:");
		
		if(driveName == null)
			return;
		
		try {
			Drive drive = new Drive(driveName, "GoogleDrive");
			View.driverList.addDrive(drive);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
