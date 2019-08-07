package view.driveframe.panel.drivepanel.bottom;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.drivepanel.bottom.cipher.Cipher;
import view.driveframe.panel.drivepanel.bottom.decipher.Decipher;
import view.driveframe.panel.drivepanel.bottom.delete.Delete;
import view.driveframe.panel.drivepanel.bottom.newfolder.NewFolder;

@SuppressWarnings("serial")
public class Bottom extends JPanel {
	
	private DrivePanel drive_panel;
	
	private Cipher cipher;
	private Decipher decipher;
	private Delete delete;
	private NewFolder new_folder;
	
	public Bottom(DrivePanel drive_panel) {
		this.drive_panel = drive_panel;
		
		cipher = new Cipher(this);
		decipher = new Decipher(this);
		delete = new Delete(this);
		new_folder = new NewFolder(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(cipher, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(decipher, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(delete, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		this.add(new_folder, constraints);
		
		this.setPreferredSize(new Dimension(80, 30));
	}
	
	public DrivePanel drive_panel() {
		return drive_panel;
	}

}
