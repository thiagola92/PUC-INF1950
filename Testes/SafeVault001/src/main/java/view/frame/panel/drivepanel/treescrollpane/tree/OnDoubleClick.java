package view.frame.panel.drivepanel.treescrollpane.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnDoubleClick implements MouseListener {
	
	private Tree tree; 
	
	public OnDoubleClick(Tree tree) {
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() <= 1)
			return;
		
		String path = tree.getSelectionPath().getLastPathComponent().toString();
		tree.updateRoot(path);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
