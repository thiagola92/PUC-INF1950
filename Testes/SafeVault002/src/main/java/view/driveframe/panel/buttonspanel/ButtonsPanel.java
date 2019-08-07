package view.driveframe.panel.buttonspanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.driveframe.panel.Panel;
import view.driveframe.panel.buttonspanel.copytodrive.CopyToDrive;
import view.driveframe.panel.buttonspanel.copytovault.CopyToVault;
import view.driveframe.panel.buttonspanel.movetodrive.MoveToDrive;
import view.driveframe.panel.buttonspanel.movetovault.MoveToVault;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	
	private CopyToVault copy_to_vault;
	private CopyToDrive copy_to_drive;
	
	private MoveToVault move_to_vault;
	private MoveToDrive move_to_drive;
	
	private Panel panel;
	
	public ButtonsPanel(Panel panel) {
		move_to_vault = new MoveToVault(this);
		move_to_drive = new MoveToDrive(this);
		
		copy_to_vault = new CopyToVault(this);
		copy_to_drive = new CopyToDrive(this);
		
		this.panel = panel;
		
		this.setLayout(new GridBagLayout());
		
		addComponent(new JLabel("move"));
		addComponent(move_to_vault);
		addComponent(move_to_drive);
		
		addComponent(new JLabel("copy"));
		addComponent(copy_to_vault);
		addComponent(copy_to_drive);
	}
	
	private void addComponent(Component component) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = this.getComponentCount();
		constraints.insets = new Insets(10, 10, 0, 10);
		
		this.add(component, constraints);
	}
	
	public Panel panel() {
		return panel;
	}

}
