package view.driveframe.panel.drivepanel.center;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.drivepanel.center.tree.Tree;

@SuppressWarnings("serial")
public class Center extends JScrollPane {
	
	private DrivePanel drive_panel;
	
	private Tree tree;
	
	public Center(DrivePanel drive_panel) {
		this.drive_panel = drive_panel;
		
		DefaultMutableTreeNode mutable = new DefaultMutableTreeNode(null);
		tree = new Tree(this, mutable);
		
		this.setViewportView(tree);
	}
	
	public DrivePanel drive_panel() {
		return drive_panel;
	}
	
	public Tree tree() {
		return tree;
	}

}
