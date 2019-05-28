package view.driveframe.panel.middlepanel.move;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.middlepanel.move.OnClick;

@SuppressWarnings("serial")
public class Move extends JButton {
	
	private DrivePanel fromDrivePanel;
	private DrivePanel toDrivePanel;
	
	public Move(String direction, DrivePanel fromDrivePanel, DrivePanel toDrivePanel) {
		this.fromDrivePanel = fromDrivePanel;
		this.toDrivePanel = toDrivePanel;
		
		createIcon(direction);
		
		this.addActionListener(new OnClick(this));
	}
	
	public DrivePanel fromDrivePanel() {
		return fromDrivePanel;
	}
	
	public DrivePanel toDrivePanel() {
		return toDrivePanel;
	}
	
	private void createIcon(String direction) {
		String path = "angle-" + direction + ".png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Deletar arquivo"));
	    } else {
	    	if("right".equals(direction))
	    		this.setText(">>");
	    	else if("left".equals(direction))
	    		this.setText("<<");
	    }
	}
	
}
