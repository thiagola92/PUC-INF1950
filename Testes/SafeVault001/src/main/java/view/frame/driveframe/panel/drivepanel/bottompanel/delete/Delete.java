package view.frame.driveframe.panel.drivepanel.bottompanel.delete;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.frame.driveframe.panel.drivepanel.bottompanel.BottomPanel;
import view.frame.driveframe.panel.drivepanel.bottompanel.delete.OnClick;

@SuppressWarnings("serial")
public class Delete extends JButton {
	
	public BottomPanel bottomPanel;
	
	public Delete(BottomPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
		
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
