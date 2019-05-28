package view.driveframe.panel.drivepanel.toppanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.drivepanel.toppanel.settingsbutton.SettingsButton;
import view.driveframe.panel.drivepanel.toppanel.treecombobox.TreeComboBox;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	
	public TreeComboBox treeComboBox = new TreeComboBox();
	public SettingsButton settingsButton = new SettingsButton();
	
	public TopPanel() {
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
