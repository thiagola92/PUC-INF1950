package view.frame.driveframe.panel.drivepanel.bottompanel.newFolder;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frame.driveframe.panel.drivepanel.bottompanel.BottomPanel;

@SuppressWarnings("serial")
public class NewFolder extends JButton {
	
	public BottomPanel bottomPanel;
	
	public NewFolder(BottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
		
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
