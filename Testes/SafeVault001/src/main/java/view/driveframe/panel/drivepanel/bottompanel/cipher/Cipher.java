package view.driveframe.panel.drivepanel.bottompanel.cipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cipher extends JButton {
	
	public Cipher() {		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "lock.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Cifrar arquivo"));
	    } else {
			this.setText("C");
	    }
	}

}
