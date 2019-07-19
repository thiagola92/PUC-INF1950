package view.frames.driveframe.panel.drivepanel.bottompanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frames.driveframe.panel.drivepanel.DrivePanel;
import view.frames.driveframe.panel.drivepanel.bottompanel.cipher.CipherButton;
import view.frames.driveframe.panel.drivepanel.bottompanel.decipher.DecipherButton;
import view.frames.driveframe.panel.drivepanel.bottompanel.delete.DeleteButton;
import view.frames.driveframe.panel.drivepanel.bottompanel.newFolder.NewFolderButton;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {
	
	public CipherButton cipherButton = new CipherButton(this);
	public DecipherButton decipherButton = new DecipherButton(this);
	public DeleteButton deleteButton = new DeleteButton(this);
	public NewFolderButton newFolderButton = new NewFolderButton(this);
	
	public DrivePanel drivePanel;
	
	public BottomPanel(DrivePanel drivePanel) {
		this.drivePanel = drivePanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(cipherButton, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(decipherButton, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.weightx = 0.1;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(newFolderButton, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(deleteButton, constraints);
	}

}
