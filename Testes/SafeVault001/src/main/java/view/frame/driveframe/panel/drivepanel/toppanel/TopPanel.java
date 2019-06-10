package view.frame.driveframe.panel.drivepanel.toppanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frame.driveframe.panel.drivepanel.DrivePanel;
import view.frame.driveframe.panel.drivepanel.toppanel.settingsbutton.SettingsButton;
import view.frame.driveframe.panel.drivepanel.toppanel.treecombobox.TreeComboBox;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	
	public TreeComboBox treeComboBox = new TreeComboBox(this);
	public SettingsButton settingsButton = new SettingsButton(this);
	
	public DrivePanel drivePanel;
	
	public TopPanel(DrivePanel drivePanel) {
		this.drivePanel = drivePanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(treeComboBox, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(settingsButton, constraints);
	}

}
