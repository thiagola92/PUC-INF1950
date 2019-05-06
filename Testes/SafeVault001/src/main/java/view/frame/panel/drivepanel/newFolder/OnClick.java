package view.frame.panel.drivepanel.newFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.drive.file.File;

public class OnClick implements ActionListener {
	
	private NewFolder newFolder;
	
	public OnClick(NewFolder newFolder) {
		this.newFolder = newFolder;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)newFolder.toDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File file = (File) node.getUserObject();

//			Engine.createFolder(file);
			
			newFolder.toDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e1) {
			System.out.format("NewFolder OnClick: %s\n", e1);
		}
	}

}
