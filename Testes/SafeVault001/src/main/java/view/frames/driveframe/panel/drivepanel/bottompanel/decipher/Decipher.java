package view.frames.driveframe.panel.drivepanel.bottompanel.decipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frames.driveframe.panel.drivepanel.bottompanel.BottomPanel;

@SuppressWarnings("serial")
public class Decipher extends JButton {
	
	public BottomPanel bottomPanel;
	
	public Decipher(BottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "unlock.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Decifrar arquivo"));
	    } else {
			this.setText("D");
	    }
	}

}
