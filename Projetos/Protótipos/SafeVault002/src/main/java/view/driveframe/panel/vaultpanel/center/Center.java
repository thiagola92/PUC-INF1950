package view.driveframe.panel.vaultpanel.center;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import view.driveframe.panel.vaultpanel.VaultPanel;
import view.driveframe.panel.vaultpanel.center.tree.Tree;

@SuppressWarnings("serial")
public class Center extends JScrollPane {
	
	private VaultPanel vault_panel;
	
	private Tree tree;
	
	public Center(VaultPanel vault_panel) {
		this.vault_panel = vault_panel;
		
		DefaultMutableTreeNode mutable = new DefaultMutableTreeNode();
		tree = new Tree(this, mutable);
		
		this.setViewportView(tree);
	}
	
	public VaultPanel vault_panel() {
		return vault_panel;
	}
	
	public Tree tree() {
		return tree;
	}

}
