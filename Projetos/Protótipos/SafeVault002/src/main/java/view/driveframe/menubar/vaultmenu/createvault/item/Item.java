package view.driveframe.menubar.vaultmenu.createvault.item;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import _main.Main;
import engine.drives.drive.Drive;
import view.driveframe.menubar.vaultmenu.createvault.CreateVault;

@SuppressWarnings("serial")
public class Item extends JMenuItem {
	
	private CreateVault create_vault;
	private Drive drive;
	
	public Item(CreateVault create_vault, Drive drive) {
		this.create_vault = create_vault;
		this.drive = drive;
		
		this.setText(drive.name());
		
		this.addActionListener((event) -> add());
	}
	
	private boolean have_publickey() {
		if(drive.vault().publickey() != null)
			return true;

		String message = Main.view.utilities().strings().utf8("Faltando chave pública");
        JOptionPane.showMessageDialog(create_vault.vault_menu().menu_bar().drive_frame(), message);
		
		return false;
	}
	
	private boolean have_privatekey() {
		if(drive.vault().privatekey() != null)
			return true;

		String message = Main.view.utilities().strings().utf8("Faltando chave privada");
        JOptionPane.showMessageDialog(create_vault.vault_menu().menu_bar().drive_frame(), message);
		
		return false;
	}
	
	private boolean have_secret_phrase() {
		if(drive.vault().secret_phrase() != null)
			return true;

		String message = Main.view.utilities().strings().utf8("Faltando frase secreta");
        JOptionPane.showMessageDialog(create_vault.vault_menu().menu_bar().drive_frame(), message);
		
		return false;
	}
	
	private void add() {
		if(!have_publickey())
			return;
		
		if(!have_privatekey())
			return;
		
		if(!have_secret_phrase())
			return;
		
		try {
			drive.vault().create();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
