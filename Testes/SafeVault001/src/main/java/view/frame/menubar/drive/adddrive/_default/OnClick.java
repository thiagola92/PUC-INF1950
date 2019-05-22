package view.frame.menubar.drive.adddrive._default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import engine.Engine;
import view.View;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String driveName = JOptionPane.showInputDialog("Drive name:");
		if(driveName == null)
			return;
		
		String path = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showOpenDialog(View.frame.menuBar.drive.addDrive._default) == JFileChooser.APPROVE_OPTION)
			path = fileChooser.getSelectedFile().getPath();
		
		try {
			if(path == null)
				Engine.driverList.addDrive(driveName, "Default");
			else
				Engine.driverList.addDrive(driveName, path, "Default");
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
	}

}
