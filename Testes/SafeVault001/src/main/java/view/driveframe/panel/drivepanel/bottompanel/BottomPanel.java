package view.driveframe.panel.drivepanel.bottompanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.drivepanel.bottompanel.cipher.Cipher;
import view.driveframe.panel.drivepanel.bottompanel.decipher.Decipher;
import view.driveframe.panel.drivepanel.bottompanel.delete.Delete;
import view.driveframe.panel.drivepanel.bottompanel.newFolder.NewFolder;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {
	
	public Cipher cipher = new Cipher();
	public Decipher decipher = new Decipher();
	public Delete delete = new Delete();
	public NewFolder newFolder = new NewFolder();
	
	public BottomPanel() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
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
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(newFolder, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(delete, constraints);
	}

}
