package view.frame.panel.drivepanel.treescrollpane.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	public Tree(DefaultMutableTreeNode root) {
		super(root);
		
		this.root = root;
		this.addMouseListener(new OnDoubleClick(this));
		this.setCellRenderer(new CellRenderer());
		
		Engine.update.addUpdateListener(new OnDriveUpdate(this));
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
		try {
			File folder = (File) root.getUserObject();
			
			Engine.listFolder(folder).forEach(file -> {
				DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
				
				root.add(fileNode);
			});
		} catch(Exception e) {
			System.out.format("Tree: %s\n", e);
		}
	}

}
