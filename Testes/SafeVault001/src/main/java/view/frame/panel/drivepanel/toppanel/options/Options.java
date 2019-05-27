package view.frame.panel.drivepanel.toppanel.options;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Options extends JButton {
	
	public Options() {		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "gear.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Configurações"));
	    } else {
			this.setText("O");
	    }
	}

}
