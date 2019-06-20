package view.frames.driveframe.panel.drivepanel.treescrollpane.tree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.InvalidKeyException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.file.File;
import engine.file.vault.index.exception.IndexNotFoundException;
import view.View;
import view.stringformat.StringFormat;

public class OnDoubleClick implements MouseListener {
	
	private Tree tree; 
	
	public OnDoubleClick(Tree tree) {
		this.tree = tree;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() <= 1)
			return;
		
		if(tree.getSelectionPath() == null)
			return;
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		File file = (File)node.getUserObject();
		
		try {
			tree.newRoot(file);
		} catch (InvalidKeyException e1) {
			StringFormat message = new StringFormat("Chave inválida para esse vault.");
			JOptionPane.showMessageDialog(View.driveFrame, message);
		} catch (IndexNotFoundException e1) {
			StringFormat message = new StringFormat("Não foi possível encontrar o index do vault '" + file.getDrive().getName() + "'.");
			JOptionPane.showMessageDialog(View.driveFrame, message);
		} catch (ArrayIndexOutOfBoundsException e1) {
			StringFormat message = new StringFormat("Index do drive '" + file.getDrive().getName() + "' está mal formulado ou não é o index do vault.");
			JOptionPane.showMessageDialog(View.driveFrame, message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
