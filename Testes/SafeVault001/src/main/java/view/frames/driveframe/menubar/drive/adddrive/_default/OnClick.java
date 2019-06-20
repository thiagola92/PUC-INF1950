package view.frames.driveframe.menubar.drive.adddrive._default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import engine.file.drive.Drive;
import view.View;
import view.drivelist.exception.NameAlreadyUsedException;
import view.stringformat.StringFormat;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String driveName = JOptionPane.showInputDialog("Drive name:");
		if(driveName == null)
			return;

		String secretPhrase = JOptionPane.showInputDialog("Frase secreta:");
		if(secretPhrase == null)
			return;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showOpenDialog(View.driveFrame.menuBar.drive.addDrive._default) != JFileChooser.APPROVE_OPTION)
			return;

		String startPath = fileChooser.getSelectedFile().getPath();
		
		try {
			Drive drive = new Drive(driveName, "Default");
			drive.setStartPath(startPath);
			drive.setSecretPhrase(secretPhrase);
			
			View.driverList.addDrive(drive);
		} catch (NameAlreadyUsedException e1) {
			StringFormat message = new StringFormat("Nome já está em uso.");
			JOptionPane.showMessageDialog(View.driveFrame, message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
