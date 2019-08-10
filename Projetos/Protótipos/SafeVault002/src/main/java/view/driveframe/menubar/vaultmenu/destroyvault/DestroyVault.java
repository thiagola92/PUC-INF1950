package view.driveframe.menubar.vaultmenu.destroyvault;

import javax.swing.JMenu;

import view.driveframe.menubar.vaultmenu.VaultMenu;
import view.driveframe.menubar.vaultmenu.destroyvault.item.Item;

@SuppressWarnings("serial")
public class DestroyVault extends JMenu {
	
	private VaultMenu vault_menu;
	
	public DestroyVault(VaultMenu vault_menu) {
		this.vault_menu = vault_menu;
		
		this.setText("Remove Vault");
		
		vault_menu.menu_bar().drive_frame().view().engine().drives().subscribe((message) -> notification());
	}
	
	public VaultMenu vault_menu() {
		return vault_menu;
	}
	
	private void notification() {
		this.removeAll();
		
		vault_menu.menu_bar()
				.drive_frame()
				.view()
				.engine()
				.drives()
				.get()
				.filter((drive) -> drive.vault().folder() != null)
				.forEach((drive) -> {
					this.add(new Item(this, drive));
				});
	}
	
}
