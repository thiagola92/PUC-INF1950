package view.driveframe.panel.drivepanel.center.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.tree.DefaultMutableTreeNode;

import engine.drives.drive.file.File;

public class OnClick implements MouseListener {
	
	private Tree tree;
	
	public OnClick(Tree tree) {
		this.tree = tree;
	}
	
	public Tree tree() {
		return tree;
	}

	public void double_click() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		File file = (File)node.getUserObject();
		
		if(file == null)
			return;
		
		try {
			tree.set_root(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount() >= 2 && tree.getSelectionPath() != null)
			double_click();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
