package view.frame.panel.drivepanel.newFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;
import view.View;
import view.frame.panel.drivepanel.DrivePanel;

public class OnClick implements ActionListener {
	
	private NewFolder newFolder;
	
	public OnClick(NewFolder newFolder) {
		this.newFolder = newFolder;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.frame.panel.firstDrivePanel.newFolder == newFolder)
			return View.frame.panel.firstDrivePanel;
		
		return View.frame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File folder = (File) node.getUserObject();
		
		String newFolderName = JOptionPane.showInputDialog("Folder name:");
		
		if(newFolderName == null)
			return;
		
		try {
			Engine.createFolder(folder, newFolderName);
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
