package view.frame.menubar.drive.adddrive.googledrive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import engine.Engine;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String driveName = JOptionPane.showInputDialog("Drive name:");
		
		if(driveName == null)
			return;
		
		try {
			Engine.driverList.addDrive(driveName, "GoogleDrive");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
