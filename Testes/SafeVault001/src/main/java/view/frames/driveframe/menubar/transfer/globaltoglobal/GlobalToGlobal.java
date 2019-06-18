package view.frames.driveframe.menubar.transfer.globaltoglobal;

import javax.swing.JMenuItem;

import view.frames.driveframe.menubar.transfer.globaltoglobal.OnClick;

@SuppressWarnings("serial")
public class GlobalToGlobal extends JMenuItem {
	
	public GlobalToGlobal() {
		this.setText("Global <=> Global");
		
		this.addActionListener(new OnClick());
	}

}
