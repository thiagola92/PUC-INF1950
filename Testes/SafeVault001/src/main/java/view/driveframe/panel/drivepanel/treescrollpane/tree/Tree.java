package view.driveframe.panel.drivepanel.treescrollpane.tree;

import java.nio.file.Path;
import java.nio.file.Paths;

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
		
		folder = new File(folder.getDrive(), folder.getPath(), folder.getType());
		
		root.setUserObject(folder);
		root.removeAllChildren();
		addReturnNode();
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
			e.printStackTrace();
		}
	}
	
	public void addReturnNode() {
		File folder = (File) root.getUserObject();
		Path returnPath = Paths.get(folder.getPath()).getParent();
		
		if(returnPath == null)
			returnPath = Paths.get("");
		
		File returnFolder = new File(folder.getDrive(), returnPath.toString(), "folder");
		DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(returnFolder);

		returnFolder.setName("..");
		root.add(returnNode);
	}

}
