package view.frame.panel.drivepanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.frame.panel.drivepanel.toppanel.TopPanel;
import view.frame.panel.drivepanel.treescrollpane.TreeScrollPane;

@SuppressWarnings("serial")
public class DrivePanel extends JPanel {
	
	public TopPanel topPanel = new TopPanel();
	public TreeScrollPane treeScrollPane = new TreeScrollPane();
	
	public DrivePanel() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(topPanel, constraints);
		
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


