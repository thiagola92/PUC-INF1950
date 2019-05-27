package view.frame.panel.drivepanel.bottompanel.decipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Decipher extends JButton {
	
	public Decipher() {		
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
