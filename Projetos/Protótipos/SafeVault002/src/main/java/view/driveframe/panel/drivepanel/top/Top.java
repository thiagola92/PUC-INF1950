package view.driveframe.panel.drivepanel.top;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.drivepanel.top.combobox.ComboBox;

@SuppressWarnings("serial")
public class Top extends JPanel {
	
	private DrivePanel drive_panel;
	
	private ComboBox combo_box;
	
	public Top(DrivePanel drive_panel) {
		this.drive_panel = drive_panel;
		
		combo_box = new ComboBox(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(combo_box, constraints);
	}
	
	public DrivePanel drive_panel() {
		return drive_panel;
	}
	
	public ComboBox combo_box() {
		return combo_box;
	}

}
