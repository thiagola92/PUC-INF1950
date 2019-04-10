package view.frame.menubar.driver.adddriver._default;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Default extends JMenuItem {
	
	public Default() {
		this.setText("Local");
		
		this.addActionListener(new OnClick(this));
	}
}
