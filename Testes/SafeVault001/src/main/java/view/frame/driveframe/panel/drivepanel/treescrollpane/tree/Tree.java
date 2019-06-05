package view.frame.driveframe.panel.drivepanel.treescrollpane.tree;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.file.vault.Vault;
import engine.file.vault.index.Index;
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
	
	public void updateRoot() throws Exception {
		newRoot((File)root.getUserObject());
	}
	
	public void newRoot(File folder) throws Exception {
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
	
	public void openRoot(File rootFolder) throws Exception {
		Engine.listFolder(rootFolder).forEach(file -> {
			DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
			
			root.add(fileNode);
		});
	}
	
	public void addReturnNode(File rootFolder) throws Exception {
		Path returnPath = Paths.get(rootFolder.getPath()).getParent();
		
		if(returnPath == null)
			return;
		
		File returnFolder = new ReturnFolder(rootFolder, returnPath.toString());
		
		if(Vault.isInsideVault(returnFolder))
			returnFolder.setName(Index.getSafeFile(returnFolder).getName());
		
		DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(returnFolder);

		root.add(returnNode);
	}

}
