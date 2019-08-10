package view.driveframe.menubar.vaultmenu.destroyvault.item;

import javax.swing.JMenuItem;

import engine.drives.drive.Drive;
import view.driveframe.menubar.vaultmenu.destroyvault.DestroyVault;

@SuppressWarnings("serial")
public class Item extends JMenuItem {
	
	private DestroyVault destroy_vault;
	private Drive drive;
	
	public Item(DestroyVault destroy_vault, Drive drive) {
		this.destroy_vault = destroy_vault;
		this.drive = drive;
		
		this.setText(drive.name());
		
		this.addActionListener((event) -> destroy());
	}
	
	public DestroyVault destroy_vault() {
		return destroy_vault;
	}
	
	public Drive drive() {
		return drive;
	}
	
	private void destroy() {
		try {
			drive.vault().destroy();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
