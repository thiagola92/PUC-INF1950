package view.frame.panel.buttonspanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.frame.panel.buttonspanel.copy.Copy;
import view.frame.panel.buttonspanel.move.Move;
import view.frame.panel.drivepanel.DrivePanel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	
	public Copy copyFromLeftToRight;
	public Copy copyFromRightToLeft;
	
	public Move moveFromLeftToRight;
	public Move moveFromRightToLeft;
	
	public ButtonsPanel(DrivePanel firstDrivePanel, DrivePanel secondDrivePanel) {		
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
