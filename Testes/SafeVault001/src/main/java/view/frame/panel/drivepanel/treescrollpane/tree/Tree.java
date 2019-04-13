package view.frame.panel.drivepanel.treescrollpane.tree;

import java.nio.file.Paths;
import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.driver.Drive;
import engine.driver.DriveFile;
import view.View;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	public DefaultMutableTreeNode root;
	
	private Drive drive;
	private HashMap<String, DefaultMutableTreeNode> nodes;
	
	public Tree(DefaultMutableTreeNode root) {
		super(root);
		
		this.root = root;
		this.nodes = new HashMap<String, DefaultMutableTreeNode>();
		this.addMouseListener(new OnDoubleClick(this));
		this.setCellRenderer(new CellRenderer());
		
		nodes.put("", root);
	}
	
	public void setDrive(Drive drive) {
		this.drive = drive;
	}
	
	private boolean isFolder(String path) {
		String fileName = Paths.get(path).getFileName().toString();
		DefaultMutableTreeNode fileNode = nodes.get(fileName);
		DriveFile driveFile = (DriveFile)fileNode.getUserObject();
		
		if(driveFile.getType().equals("folder"))
			return true;
		
		return false;
	}
	
	public void resetRoot() {
		root.removeAllChildren();
		nodes.clear();
		
		this.updateUI();
	}
	
	public void updateRoot(String folderName) {	
		System.out.println(">>1 " + folderName);	
		DriveFile folder = (DriveFile)nodes.get(folderName).getUserObject();
		String folderPath = folder.getPath();
		System.out.println(">>2 " + folderPath);

		if(isFolder(folderName) == false)
			return;
		
		resetRoot();
		createRoot(folderPath);
		openRoot(folderPath);
		
		this.updateUI();
	}
	
	public void createRoot(String path) {
		DriveFile driveFile = new DriveFile(path, "folder");
		root.setUserObject(driveFile);
		nodes.put(driveFile.getName(), root);
	}
	
	public void openRoot(String path) {
		try {
			View.engine.listFolder(drive, path).forEach(file -> {
				DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file);
				
				root.add(fileNode);
				System.out.println(">>3 " + file.getName());
				nodes.put(file.getName(), fileNode);
			});
		} catch (Exception e) {
			System.out.format("Tree: %s\n", e);
		}
	}

}
