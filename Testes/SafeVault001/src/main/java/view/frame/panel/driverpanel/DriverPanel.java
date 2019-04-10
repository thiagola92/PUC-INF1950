package view.frame.panel.driverpanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frame.panel.driverpanel.treecombobox.TreeComboBox;
import view.frame.panel.driverpanel.treescrollpane.TreeScrollPane;

@SuppressWarnings("serial")
public class DriverPanel extends JPanel {
	
	public TreeComboBox treeComboBox = new TreeComboBox();
	public TreeScrollPane treeScrollPane = new TreeScrollPane();
	
	public DriverPanel() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		this.add(treeComboBox, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(treeScrollPane, constraints);
		
		treeComboBox.addActionListener(treeScrollPane);
	}
}


