package view.driveframe.panel.vaultpanel.bottom;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.vaultpanel.VaultPanel;
import view.driveframe.panel.vaultpanel.bottom.delete.Delete;
import view.driveframe.panel.vaultpanel.bottom.newfolder.NewFolder;

@SuppressWarnings("serial")
public class Bottom extends JPanel {
	
	private VaultPanel vault_panel;

	private Delete delete;
	private NewFolder new_folder;
	
	public Bottom(VaultPanel vault_panel) {
		this.vault_panel = vault_panel;

		delete = new Delete(this);
		new_folder = new NewFolder(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(delete, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		this.add(new_folder, constraints);
		
		this.setPreferredSize(new Dimension(80, 30));
	}
	
	public VaultPanel vault_panel() {
		return vault_panel;
	}

}
