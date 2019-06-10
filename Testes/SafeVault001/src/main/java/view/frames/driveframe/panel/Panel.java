package view.frames.driveframe.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import view.frames.driveframe.panel.drivepanel.DrivePanel;
import view.frames.driveframe.panel.drivepanel._enumeration.mode.DrivePanelMode;
import view.frames.driveframe.panel.middlepanel.MiddlePanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public DrivePanel leftDrivePanel = new DrivePanel();
	public DrivePanel rightDrivePanel = new DrivePanel();
	public MiddlePanel middlePanel = new MiddlePanel(leftDrivePanel, rightDrivePanel);
	
	public Panel() {
		this.leftDrivePanel.drivePanelMode = DrivePanelMode.NORMAL_MODE;
		this.rightDrivePanel.drivePanelMode = DrivePanelMode.VAULT_MODE;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 0);
		constraints.fill = GridBagConstraints.BOTH;
		this.add(leftDrivePanel, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		this.add(middlePanel, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 0, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		this.add(rightDrivePanel, constraints);
	}
}
