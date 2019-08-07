package view.driveframe.menubar.vaultmenu.removevault.item;

import javax.swing.JMenuItem;

import engine.drives.drive.Drive;
import view.driveframe.menubar.vaultmenu.removevault.RemoveVault;

@SuppressWarnings("serial")
public class Item extends JMenuItem {
	
	private RemoveVault remove_vault;
	private Drive drive;
	
	public Item(RemoveVault remove_vault, Drive drive) {
		this.remove_vault = remove_vault;
		this.drive = drive;
		
		this.setText(drive.name());
		
		this.addActionListener((event) -> remove());
	}
	
	public RemoveVault remove_vault() {
		return remove_vault;
	}
	
	public Drive drive() {
		return drive;
	}
	
	private void remove() {
		
	}

}
