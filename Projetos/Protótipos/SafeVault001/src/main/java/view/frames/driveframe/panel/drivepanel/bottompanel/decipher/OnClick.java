package view.frames.driveframe.panel.drivepanel.bottompanel.decipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.BadPaddingException;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.frames.loadingframe.LoadingFrame;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private DecipherButton decipherButton;
	
	public OnClick(DecipherButton decipherButton) {
		this.decipherButton = decipherButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Tree tree = decipherButton.bottomPanel.drivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();
		
		View.loadingFrame = new LoadingFrame();
		
		try {
			Engine.decipher(file);
			
			View.loadingFrame.dispose();
			
            JOptionPane.showMessageDialog(View.driveFrame, "Decifrado com sucesso.");
			
            View.update.updateListeners(UpdateOptions.FILE_UPDATED);
		} catch (BadPaddingException e1) {
            JOptionPane.showMessageDialog(View.driveFrame, "Falha ao tentar decifrar.");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			View.loadingFrame.dispose();
		}
	}

}
