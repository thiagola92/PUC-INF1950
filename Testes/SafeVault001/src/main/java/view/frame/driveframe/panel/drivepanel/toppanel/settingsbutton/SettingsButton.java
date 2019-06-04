package view.frame.driveframe.panel.drivepanel.toppanel.settingsbutton;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SettingsButton extends JButton {
	
	public SettingsButton() {		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "gear.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Configurações"));
	    } else {
			this.setText("S");
	    }
	}

}
