package view.frame.panel.drivepanel.toppanel.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;
import view.View;
import view.frame.panel.drivepanel.DrivePanel;

public class OnClick implements ActionListener {
	
	private Options options;
	
	public OnClick(Options options) {
		this.options = options;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.frame.panel.firstDrivePanel.topPanel.options == options)
			return View.frame.panel.firstDrivePanel;
		
		return View.frame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
//		File file = (File) node.getUserObject();
//		
//		try {
//			Engine.cipher(file);
//			
//			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
	}

}
