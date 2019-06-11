package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.BadPaddingException;
import javax.swing.JOptionPane;

import engine.file.File;
import engine.file.drive.Drive;
import engine.file.vault.Vault;
import view.View;
import view.frames.driveframe.panel.drivepanel._enumeration.mode.DrivePanelMode;

public class OnChange implements ActionListener {
	
	private TreeComboBox treeComboBox;
	
	public OnChange(TreeComboBox treeComboBox) {
		this.treeComboBox = treeComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(treeComboBox.getItemCount() == 0)
			return;
		
		Drive drive = (Drive)treeComboBox.getSelectedItem();		
		File file = new File(drive, drive.getStartPath(), "folder");
		File vault = getVault(file);
		
		if(treeComboBox.topPanel.drivePanel.drivePanelMode == DrivePanelMode.VAULT_MODE)
			file = vault;
		
		try {
			treeComboBox.topPanel.drivePanel.treeScrollPane.tree.newRoot(file);
		} catch (BadPaddingException e1) {
            JOptionPane.showMessageDialog(View.driveFrame, "Esse SafeVault nao esta ligado a sua chave privada/publica");
            
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public File getVault(File root) {		
		try {
			if(Vault.existVault(root))
				return Vault.getVault(root);
			else
				return Vault.createSafeVault(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
