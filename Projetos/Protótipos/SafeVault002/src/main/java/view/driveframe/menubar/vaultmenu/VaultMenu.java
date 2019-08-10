package view.driveframe.menubar.vaultmenu;

import javax.swing.JMenu;

import view.driveframe.menubar.MenuBar;
import view.driveframe.menubar.vaultmenu.createvault.CreateVault;
import view.driveframe.menubar.vaultmenu.destroyvault.DestroyVault;

@SuppressWarnings("serial")
public class VaultMenu extends JMenu {
	
	private MenuBar menu_bar;
	
	private CreateVault create_vault;
	private DestroyVault remove_vault;
	
	public VaultMenu(MenuBar menu_bar) {
		this.menu_bar = menu_bar;
		
		create_vault = new CreateVault(this);
		remove_vault = new DestroyVault(this);
		
		this.setText("Vault");
		
		this.add(create_vault);
		this.add(remove_vault);
	}
	
	public MenuBar menu_bar() {
		return menu_bar;
	}

}
