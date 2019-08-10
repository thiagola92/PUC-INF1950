package view.frames.driveframe.panel.drivepanel.toppanel.newvaultbutton;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frames.driveframe.panel.drivepanel.toppanel.TopPanel;

@SuppressWarnings("serial")
public class NewVaultButton extends JButton {
	
	public TopPanel topPanel;
	
	public NewVaultButton(TopPanel topPanel) {
		this.topPanel = topPanel;
		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "vault.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Configurações"));
	    } else {
			this.setText("S");
	    }
	}
}
