package view.driveframe.panel.vaultpanel.center.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.Drive;
import engine.drives.drive.file.File;
import view.driveframe.panel.vaultpanel.center.tree.CellRenderer;
import view.driveframe.panel.vaultpanel.center.tree.OnClick;
import view.driveframe.panel.vaultpanel.center.Center;

@SuppressWarnings("serial")
public class Tree extends JTree {
	
	private Center center;
	private DefaultMutableTreeNode root;
	
	public Tree(Center center, DefaultMutableTreeNode root) {
		super(root);
		
		this.center = center;
		this.root = root;
		
		this.setCellRenderer(new CellRenderer());
		this.addMouseListener(new OnClick(this));
	}
	
	public Center center() {
		return center;
	}
	
	public DefaultMutableTreeNode root() {
		return root;
	}
	
	public void drive(Drive drive) throws Exception {
		set_root(drive.vault().folder());
	}
	
	public void set_root(File folder) throws Exception {
		if(folder.type().equals("file"))
			return;
		
		folder.set_to_string(null);
		
		root.setUserObject(folder);
		root.removeAllChildren();

		parent(folder.parent());
		open(folder);
		
		this.updateUI();
	}
	
	public void clean() {
		root.removeAllChildren();
		root.setUserObject(null);
		this.updateUI();
	}
	
	public void update() throws Exception {
		File file = (File)root.getUserObject();
		
		if(file != null)
			set_root(file);
	}
	
	private void open(File folder) throws Exception {
		Main.view.engine().actions().list().folder(folder).forEach(file -> {
			root.add(new DefaultMutableTreeNode(file));
		});
	}
	
	private void parent(File parent) {
		if(parent == null)
			return;
		
		parent.set_to_string("..");
		root.add(new DefaultMutableTreeNode(parent));
	}

}
