package view.frame.panel.drivepanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frame.panel.drivepanel.delete.Delete;
import view.frame.panel.drivepanel.newFolder.NewFolder;
import view.frame.panel.drivepanel.treecombobox.TreeComboBox;
import view.frame.panel.drivepanel.treescrollpane.TreeScrollPane;

@SuppressWarnings("serial")
public class DrivePanel extends JPanel {
	
	public TreeComboBox treeComboBox = new TreeComboBox();
	public TreeScrollPane treeScrollPane = new TreeScrollPane();
	public Delete delete = new Delete(this);
	public NewFolder newFolder = new NewFolder(this);
	
	public DrivePanel() {
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
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(treeScrollPane, constraints);
	}
}


