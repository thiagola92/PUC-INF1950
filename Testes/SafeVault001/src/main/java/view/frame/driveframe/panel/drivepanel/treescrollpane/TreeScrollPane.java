package view.frame.driveframe.panel.drivepanel.treescrollpane;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.file.File;
import view.frame.driveframe.panel.drivepanel.DrivePanel;
import view.frame.driveframe.panel.drivepanel.treescrollpane.tree.Tree;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane {
	
	public Tree tree;
	
	public DrivePanel drivePanel;
	
	public TreeScrollPane(DrivePanel drivePanel) {
		this.drivePanel = drivePanel;
		
		File file = new File(null, "", "folder");
		tree = new Tree(new DefaultMutableTreeNode(file));
		
		this.setViewportView(tree);
	}

}
