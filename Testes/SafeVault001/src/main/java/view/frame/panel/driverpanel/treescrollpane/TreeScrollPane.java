package view.frame.panel.driverpanel.treescrollpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane implements ActionListener {
	
	public TreeScrollPane() {		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
		
		JTree tree = new JTree(root);
		
		this.setViewportView(tree);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.getActionCommand());
//		System.out.println(e.getID());
//		System.out.println(e.getModifiers());
//		System.out.println(e.getSource());
//		System.out.println(e.getWhen());
//		System.out.println(e.getClass());
		
	}

//	@Override
//	public void engineUpdated() {
//		
//		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
//		
//		///////////
//		
//		DefaultMutableTreeNode example = new DefaultMutableTreeNode("example");
//		root.add(example);
//		
//		DefaultMutableTreeNode example2 = new DefaultMutableTreeNode("Aexample");
//		root.add(example2);
//		
//		///////////
//		
//		JTree tree = new JTree(root);
//		
//		this.setViewportView(tree);
//		
//	}

}
