package view.driveframe.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import view.driveframe.DriveFrame;
import view.driveframe.panel.buttonspanel.ButtonsPanel;
import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.vaultpanel.VaultPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	private DriveFrame drive_frame;
	
	private DrivePanel drive_panel;
	private VaultPanel vault_panel;
	private ButtonsPanel buttons_panel;
	
	public Panel(DriveFrame drive_frame) {
		this.drive_frame = drive_frame;
		
		drive_panel = new DrivePanel(this);
		vault_panel = new VaultPanel(this);
		buttons_panel = new ButtonsPanel(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 0);
		constraints.fill = GridBagConstraints.BOTH;
		this.add(drive_panel, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		this.add(buttons_panel, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 0, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		this.add(vault_panel, constraints);
	}
	
	public DriveFrame drive_frame() {
		return drive_frame;
	}
	
	public DrivePanel drive_panel() {
		return drive_panel;
	}
	
	public VaultPanel vault_panel() {
		return vault_panel;
	}
}
