package view.frame.panel.driverpanel.treescrollpane;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.driver.Drive;

@SuppressWarnings("serial")
public class TreeScrollPane extends JScrollPane {
	
	public DefaultMutableTreeNode root;
	
	public TreeScrollPane() {
		resetRoot();
	}
	
	public void resetRoot() {
		root = new DefaultMutableTreeNode("");
		
		this.setViewportView(new JTree(root));
	}
	
	public void add(Drive drive, String path) {
		try {
			ArrayList<String[]> files = drive.getPlugin().listFolder(path);
			
			files.stream().forEach(file -> {
				root.add(new DefaultMutableTreeNode(file[0]));
			});
		} catch (Exception e1) {
			System.out.format("TreeScrollPane: %s\n", e1);
		}
	}

}
