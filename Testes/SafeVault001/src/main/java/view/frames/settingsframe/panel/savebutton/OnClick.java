package view.frames.settingsframe.panel.savebutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.settingsframe.panel.Panel;

public class OnClick implements ActionListener {
	
	private Panel panel;
	
	public OnClick(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(panel.privateKey != null)
			panel.drive.setPrivateKey(panel.privateKey);
		
		if(panel.publicKey != null)
			panel.drive.setPublicKey(panel.publicKey);
		
		try {
			String newStartPath = panel.startPath.getText();
			File folder = new File(panel.drive, newStartPath, "folder");
			Engine.listFolder(folder);
			
			panel.drive.setStartPath(newStartPath);
		} catch (Exception e) {
			String message = "Não foi possível listar essa pasta como pasta inicial.";
			message = new String(message.getBytes(), StandardCharsets.UTF_8);
			JOptionPane.showMessageDialog(View.driveFrame, message);
			
			e.printStackTrace();
		}

		panel.settingsFrame.dispose();
	}

}
