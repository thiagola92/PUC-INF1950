package view.frame.panel.drivepanel.treescrollpane.tree;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.driver.Drive;
import engine.driver.DriveFile;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	public Tree(DefaultMutableTreeNode root) {
		super(root);
		
		this.root = root;
		this.addMouseListener(new OnDoubleClick(this));
		this.setCellRenderer(new CellRenderer());
	}
	
	public void resetRoot() {
		root.removeAllChildren();
		
		this.collapseRow(0);
		this.updateUI();
	}
	
	public void add(Drive drive, String path) {
		try {
			ArrayList<String[]> files = drive.getPlugin().listFolder(path);
			
			files.stream().forEach(file -> {
				DriveFile driveFile = new DriveFile(file[0], file[1]);
				root.add(new DefaultMutableTreeNode(driveFile));
			});
		} catch (Exception e1) {
			System.out.format("TreeScrollPane: %s\n", e1);
		}
	}

}
