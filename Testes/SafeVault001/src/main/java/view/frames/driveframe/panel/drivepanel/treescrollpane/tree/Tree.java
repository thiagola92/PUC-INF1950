package view.frames.driveframe.panel.drivepanel.treescrollpane.tree;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.file.action.List;
import engine.file.vault.Vault;
import engine.file.vault.index.Index;
import view.View;
import view.frames.driveframe.panel.drivepanel._enumeration.mode.DrivePanelMode;
import view.frames.driveframe.panel.drivepanel.treescrollpane.TreeScrollPane;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.returnfolder.ReturnFolder;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	public TreeScrollPane treeScrollPane;
	
	public Tree(DefaultMutableTreeNode root, TreeScrollPane treeScrollPane) {
		super(root);
		
		this.treeScrollPane = treeScrollPane;
		
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
		ArrayList<File> files;
		
		if(treeScrollPane.drivePanel.drivePanelMode == DrivePanelMode.NORMAL_MODE)
			files = List.listFolder(rootFolder);
		else
			files = Engine.listFolder(rootFolder);
		
		files.forEach(file -> {
			DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
			
			root.add(fileNode);
		});
	}
	
	public void addReturnNode(File rootFolder) throws Exception {		
		Path rootPath = Paths.get(rootFolder.getPath());
		String returnPath = rootFolder.getDrive().getStartPath();
		
		if(rootPath.toString().equals(returnPath))
			return;
		
		if(rootPath.getParent() != null)
			returnPath = rootPath.getParent().toString();
		
		File returnFolder = new ReturnFolder(rootFolder, returnPath.toString());
		
		if(treeScrollPane.drivePanel.drivePanelMode == DrivePanelMode.VAULT_MODE)
			if(Vault.isInsideVault(returnFolder) == false)
				return;
		
		if(Vault.isInsideVault(returnFolder))
			returnFolder.setName(Index.getSafeFile(returnFolder).getName());
		
		DefaultMutableTreeNode returnNode = new DefaultMutableTreeNode(returnFolder);

		root.add(returnNode);
	}
	
	public void cleanChildren() {
		root.removeAllChildren();
		
		File rootWithoutName = (File)root.getUserObject();
		rootWithoutName.setName("");
		
		this.updateUI();
	}

}
