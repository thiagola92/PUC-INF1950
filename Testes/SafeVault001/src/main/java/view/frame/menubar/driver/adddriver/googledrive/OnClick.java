package view.frame.menubar.driver.adddriver.googledrive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			View.engine.driverList.addDrive("Google Drive", "GoogleDrive");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
