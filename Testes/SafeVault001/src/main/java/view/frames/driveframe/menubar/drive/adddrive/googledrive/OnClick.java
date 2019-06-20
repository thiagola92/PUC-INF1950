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
		
		String secretPhrase = JOptionPane.showInputDialog("Frase secreta:");
		if(secretPhrase == null)
			return;
		
		try {
			Drive drive = new Drive(driveName, "GoogleDrive");
			drive.setSecretPhrase(secretPhrase);
			
			View.driverList.addDrive(drive);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
