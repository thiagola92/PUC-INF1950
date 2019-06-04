package view.frame.driveframe.menubar.drive.adddrive._default;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Default extends JMenuItem {
	
	public Default() {
		this.setText("Local");
		
		this.addActionListener(new OnClick());
	}
}
