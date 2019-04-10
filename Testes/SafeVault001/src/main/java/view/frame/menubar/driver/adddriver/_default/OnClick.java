package view.frame.menubar.driver.adddriver._default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import engine.driver.Drive;
import engine.driver.DriveList;
import view.View;

public class OnClick implements ActionListener {
	
	private JMenuItem menuItem;
	
	public OnClick(JMenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Creating Default Driver");
		
		try {
			View.engine.driverList.addDrive("Local", "Default");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		JFileChooser fileChooser = new JFileChooser();
//		
//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		
//		int answer = fileChooser.showOpenDialog(menuItem);
//		
//		if(answer == JFileChooser.APPROVE_OPTION) {
//			File folder = fileChooser.getSelectedFile();
//		}
		
	}

}
