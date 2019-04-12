package view.frame.panel.drivepanel.treescrollpane;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.driver.DriveFile;
import view.frame.panel.drivepanel.treescrollpane.tree.Tree;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane {
	
	public Tree tree;
	
	public TreeScrollPane() {
		DriveFile driveFile = new DriveFile("", "folder");
		tree = new Tree(new DefaultMutableTreeNode(driveFile));
		
		this.setViewportView(tree);
	}

}
