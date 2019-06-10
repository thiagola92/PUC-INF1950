package view.frames.driveframe.menubar.transfer;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public class Transfer extends JMenu {
	
	public Transfer() {
		this.setText("Transfer");
		
		this.add("NORMAL <-> VAULT");
		this.add("GLOBAL <-> GLOBAL");
	}

}
