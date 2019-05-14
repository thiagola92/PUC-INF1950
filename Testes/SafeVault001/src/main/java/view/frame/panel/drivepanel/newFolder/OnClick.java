package view.frame.panel.drivepanel.newFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;

public class OnClick implements ActionListener {
	
	private NewFolder newFolder;
	
	public OnClick(NewFolder newFolder) {
		this.newFolder = newFolder;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)newFolder.toDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File folder = (File) node.getUserObject();
			
			String newFolderName = JOptionPane.showInputDialog("Folder name:");
			System.out.println(newFolderName);

			Engine.createFolder(folder, newFolderName);
			
			newFolder.toDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
