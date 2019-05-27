package view.frame.panel.drivepanel.delete;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frame.panel.drivepanel.DrivePanel;
import view.frame.panel.drivepanel.delete.OnClick;

@SuppressWarnings("serial")
public class Delete extends JButton {
	
	public Delete(DrivePanel fromDrivePanel) {		
		createIcon();
		
		this.addActionListener(new OnClick(this));
	}
	
	private void createIcon() {
		String path = "trash.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Deletar arquivo"));
	    } else {
			this.setText("X");
	    }
	}
}
