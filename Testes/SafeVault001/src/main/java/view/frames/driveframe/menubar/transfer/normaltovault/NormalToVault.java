package view.frames.driveframe.menubar.transfer.normaltovault;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class NormalToVault extends JMenuItem {
	
	public NormalToVault() {
		this.setText("Normal <-> Vault");
		
		this.addActionListener(new OnClick());
	}

}
