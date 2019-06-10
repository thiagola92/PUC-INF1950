package view.frames.driveframe.menubar.help;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Help extends JMenu {

	public Help() {
		this.setText("Help");
		
		JMenuItem about = new JMenuItem("About");
		
		this.add(about);
	}
}
