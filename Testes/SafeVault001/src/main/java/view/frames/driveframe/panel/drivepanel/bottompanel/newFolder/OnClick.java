package view.frames.driveframe.panel.drivepanel.bottompanel.newFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.frames.loadingframe.LoadingFrame;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private NewFolder newFolder;
	
	public OnClick(NewFolder newFolder) {
		this.newFolder = newFolder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tree tree = newFolder.bottomPanel.drivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File folder = (File) node.getUserObject();
		
		String newFolderName = JOptionPane.showInputDialog("Nome da pasta:");
		
		if(newFolderName == null)
			return;
		
		View.loadingFrame = new LoadingFrame();
		
		try {			
			Engine.createFolder(folder, newFolderName);
			
			View.update.updateListeners(UpdateOptions.FILE_UPDATED);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			View.loadingFrame.dispose();
		}
	}

}
