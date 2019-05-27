package view.frame.panel.drivepanel.toppanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frame.panel.drivepanel.toppanel.delete.Delete;
import view.frame.panel.drivepanel.toppanel.newFolder.NewFolder;
import view.frame.panel.drivepanel.toppanel.treecombobox.TreeComboBox;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	
	public TreeComboBox treeComboBox = new TreeComboBox();
	public Delete delete = new Delete();
	public NewFolder newFolder = new NewFolder();
	
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
		this.add(newFolder, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(delete, constraints);
	}

}
