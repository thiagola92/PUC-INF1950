package view.frames.driveframe.menubar.transfer;

import javax.swing.JMenu;

import view.frames.driveframe.menubar.transfer.globaltoglobal.GlobalToGlobal;
import view.frames.driveframe.menubar.transfer.normaltovault.NormalToVault;

@SuppressWarnings("serial")
public class Transfer extends JMenu {
	
	public GlobalToGlobal globalToGlobal = new GlobalToGlobal();
	public NormalToVault normalToVault = new NormalToVault();
	
	public Transfer() {
		this.setText("Transfer");
		
		this.add(globalToGlobal);
		this.add(normalToVault);
	}

}
