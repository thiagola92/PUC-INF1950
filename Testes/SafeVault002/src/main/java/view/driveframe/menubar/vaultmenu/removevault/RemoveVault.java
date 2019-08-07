package view.driveframe.menubar.vaultmenu.removevault;

import javax.swing.JMenu;

import view.driveframe.menubar.vaultmenu.VaultMenu;
import view.driveframe.menubar.vaultmenu.removevault.item.Item;

@SuppressWarnings("serial")
public class RemoveVault extends JMenu {
	
	private VaultMenu vault_menu;
	
	public RemoveVault(VaultMenu vault_menu) {
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
