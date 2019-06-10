package view.frame.driveframe.panel.drivepanel.toppanel.settingsbutton;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frame.driveframe.panel.drivepanel.toppanel.TopPanel;

@SuppressWarnings("serial")
public class SettingsButton extends JButton {
	
	public TopPanel topPanel;
	
	public SettingsButton(TopPanel topPanel) {
		this.topPanel = topPanel;
		
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
