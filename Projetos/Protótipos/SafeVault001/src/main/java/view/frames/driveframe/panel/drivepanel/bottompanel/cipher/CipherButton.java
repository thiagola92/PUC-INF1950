package view.frames.driveframe.panel.drivepanel.bottompanel.cipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frames.driveframe.panel.drivepanel.bottompanel.BottomPanel;

@SuppressWarnings("serial")
public class CipherButton extends JButton {
	
	public OnClick onClick = new OnClick(this);
	
	public BottomPanel bottomPanel;
	
	public CipherButton(BottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
		
		createIcon();
		
		this.addActionListener(onClick);
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
