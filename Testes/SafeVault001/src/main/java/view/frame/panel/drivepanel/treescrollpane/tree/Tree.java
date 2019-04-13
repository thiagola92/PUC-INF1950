package view.frame.panel.drivepanel.treescrollpane.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.drive.Drive;
import engine.drive.file.File;
import view.View;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	private Drive drive;
	
	public Tree(DefaultMutableTreeNode root) {
		super(root);
		
		this.root = root;
		this.addMouseListener(new OnDoubleClick(this));
		this.setCellRenderer(new CellRenderer());
	}
	
	public void setDrive(Drive drive) {
		this.drive = drive;
	}
	
	public void updateRoot() {
		newRoot((File)root.getUserObject());
	}
	
	public void newRoot(File folder) {
		if(folder.getType().equals("file"))
			return;
		
		root.setUserObject(folder);
		root.removeAllChildren();
		openRoot();
		
		this.updateUI();
	}
	
	public void openRoot() {
		File folder = (File) root.getUserObject();
		
		try {
			View.engine.listFolder(drive, folder).forEach(file -> {
				DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
				
				root.add(fileNode);
				System.out.println(">>3 " + file.getName());
			});
		} catch (Exception e) {
			System.out.format("Tree: %s\n", e);
		}
	}

}
