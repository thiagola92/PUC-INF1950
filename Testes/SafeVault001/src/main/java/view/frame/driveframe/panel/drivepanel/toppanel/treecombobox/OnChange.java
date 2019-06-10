package view.frame.driveframe.panel.drivepanel.toppanel.treecombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.Engine;
import engine.file.File;
import engine.file.drive.Drive;
import engine.file.vault.Vault;
import view.frame.driveframe.panel.drivepanel.drivepaneltype.DrivePanelType;

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
		
		if(treeComboBox.topPanel.drivePanel.drivePanelType == DrivePanelType.VAULT_MODE)
			file = vault;
		
		try {			
			treeComboBox.topPanel.drivePanel.treeScrollPane.tree.newRoot(file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public File getVault(File root) {
		File vault = null;
		
		try {
			vault = Vault.getVault(Engine.listFolder(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(vault == null)
				return Vault.createSafeVault(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vault;
	}
}
