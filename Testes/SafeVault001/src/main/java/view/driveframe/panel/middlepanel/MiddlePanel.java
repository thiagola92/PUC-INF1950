package view.driveframe.panel.middlepanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.middlepanel.copy.Copy;
import view.driveframe.panel.middlepanel.move.Move;

@SuppressWarnings("serial")
public class MiddlePanel extends JPanel {
	
	public Copy copyFromLeftToRight;
	public Copy copyFromRightToLeft;
	
	public Move moveFromLeftToRight;
	public Move moveFromRightToLeft;
	
	public MiddlePanel(DrivePanel firstDrivePanel, DrivePanel secondDrivePanel) {		
		this.setLayout(new GridBagLayout());
		
		moveFromLeftToRight = new Move("right", firstDrivePanel, secondDrivePanel);
		moveFromRightToLeft = new Move("left", secondDrivePanel, firstDrivePanel);
		
		copyFromLeftToRight = new Copy("right", firstDrivePanel, secondDrivePanel);
		copyFromRightToLeft = new Copy("left", secondDrivePanel, firstDrivePanel);
		
		addComponent(new JLabel("move"));
		addComponent(moveFromLeftToRight);
		addComponent(moveFromRightToLeft);
		
		addComponent(new JLabel("copy"));
		addComponent(copyFromLeftToRight);
		addComponent(copyFromRightToLeft);
	}
	
	private void addComponent(Component component) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = this.getComponentCount();
		constraints.insets = new Insets(10, 10, 0, 10);
		
		this.add(component, constraints);
	}

}
