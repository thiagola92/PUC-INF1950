package view.frame.driveframe.panel.drivepanel.treescrollpane.tree;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.file.vault.Vault;
import view.View;
import view.frame.driveframe.panel.drivepanel.treescrollpane.tree.returnfolder.ReturnFolder;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	public Tree(DefaultMutableTreeNode root) {
		super(root);
		
		this.root = root;
		this.addMouseListener(new OnDoubleClick(this));
		this.setCellRenderer(new CellRenderer());
		
		View.update.addUpdateListener(new OnDriveUpdate(this));
	}
	
	public void updateRoot() {
		newRoot((File)root.getUserObject());
	}
	
	public void newRoot(File folder) {
		if(folder.getType().equals("file"))
			return;
		
		File rootFolder = new File(folder.getDrive(), folder.getPath(), folder.getType());
		rootFolder.setName(folder.getName());
		
		root.setUserObject(rootFolder);
		root.removeAllChildren();
		
		addReturnNode(rootFolder);
		openRoot(rootFolder);
		
		this.updateUI();
	}
	
	public void openRoot(File rootFolder) {
		try {			
			Engine.listFolder(rootFolder).forEach(file -> {
				DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
				
				root.add(fileNode);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addReturnNode(File rootFolder) {
		Path returnPath = Paths.get(rootFolder.getPath()).getParent();
		
		if(returnPath == null)
			return;
		
		File returnFolder = new ReturnFolder(rootFolder.getDrive(), returnPath.toString(), "folder");
		if(Vault.isInsideVault(returnFolder)) {
			
		}
		
		DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(returnFolder);

		root.add(returnNode);
	}

}
