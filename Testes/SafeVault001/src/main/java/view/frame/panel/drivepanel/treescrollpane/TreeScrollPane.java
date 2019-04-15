package view.frame.panel.drivepanel.treescrollpane;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.drive.file.File;
import view.frame.panel.drivepanel.treescrollpane.tree.Tree;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane {
	
	public Tree tree;
	
	public TreeScrollPane() {
		File file = new File(null, "", "folder");
		tree = new Tree(new DefaultMutableTreeNode(file));
		
		this.setViewportView(tree);
	}

}
