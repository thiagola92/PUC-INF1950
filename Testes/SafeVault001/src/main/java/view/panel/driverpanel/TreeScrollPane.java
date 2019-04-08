package view.panel.driverpanel;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane{
	
	public TreeScrollPane() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
		
		///////////
		
		DefaultMutableTreeNode example = new DefaultMutableTreeNode("example");
		root.add(example);
		
		DefaultMutableTreeNode example2 = new DefaultMutableTreeNode("Aexample");
		root.add(example2);
		
		///////////
		
		JTree tree = new JTree(root);
		
		this.setViewportView(tree);
	}

}
