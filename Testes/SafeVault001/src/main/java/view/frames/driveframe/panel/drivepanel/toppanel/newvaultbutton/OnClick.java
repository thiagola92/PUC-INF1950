package view.frames.driveframe.panel.drivepanel.toppanel.newvaultbutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import engine.file.File;
import engine.file.drive.Drive;
import engine.file.vault.Vault;
import view.View;
import view.frames.driveframe.panel.drivepanel.toppanel.treecombobox.TreeComboBox;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private NewVaultButton newVaultButton;
	
	public OnClick(NewVaultButton newVaultButton) {
		this.newVaultButton = newVaultButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TreeComboBox treeComboBox = newVaultButton.topPanel.treeComboBox;
		Drive drive = (Drive)treeComboBox.getSelectedItem();		
		File file = new File(drive, drive.getStartPath(), "folder");
		
		try {
			if(Vault.existVault(file)) {
				String message = "Vault já existe.";
				message = new String(message.getBytes(), StandardCharsets.UTF_8);
				JOptionPane.showMessageDialog(View.driveFrame, message);
				
				return;
			}
			
			Vault.createSafeVault(file);
			
			String message = "Vault criado com sucesso.";
			message = new String(message.getBytes(), StandardCharsets.UTF_8);
			JOptionPane.showMessageDialog(View.driveFrame, message);
			
			View.update.updateListeners(UpdateOptions.DRIVE_UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}