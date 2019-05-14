package view.frame.menubar.drive.adddrive._default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.View;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String driveName = JOptionPane.showInputDialog("Drive name:");
		
		if(driveName == null)
			return;
		
		try {
			View.engine.driverList.addDrive(driveName, "Default");
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
	}

}
