package view.frames.driveframe.menubar.transfer.normaltovault;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public class NormalToVault extends JMenu {
	
	public NormalToVault() {
		this.setText("Normal <-> Vault");
		
		this.add(new OnClick());
	}

}
