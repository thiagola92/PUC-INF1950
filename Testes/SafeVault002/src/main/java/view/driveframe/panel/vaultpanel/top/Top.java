package view.driveframe.panel.vaultpanel.top;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.vaultpanel.VaultPanel;
import view.driveframe.panel.vaultpanel.top.combobox.ComboBox;

@SuppressWarnings("serial")
public class Top extends JPanel {
	
	private VaultPanel vault_panel;
	
	private ComboBox combo_box;
	
	public Top(VaultPanel vault_panel) {
		this.vault_panel = vault_panel;
		
		combo_box = new ComboBox(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(combo_box, constraints);
	}
	
	public VaultPanel vault_panel() {
		return vault_panel;
	}

}
