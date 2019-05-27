package view.frame.panel.drivepanel.newFolder;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frame.panel.drivepanel.DrivePanel;

@SuppressWarnings("serial")
public class NewFolder extends JButton {
	
	public NewFolder(DrivePanel toDrivePanel) {		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "folder-plus.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Criar pasta"));
	    } else {
			this.setText("+");
	    }
	}

}
