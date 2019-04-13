package view.frame.panel.buttonspanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import engine.drive.Drive;
import engine.drive.file.File;
import view.View;

public class OnClick implements ActionListener {
	
	private Delete delete;
	
	public OnClick(Delete delete) {
		this.delete = delete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Drive drive = (Drive)delete.fromDrivePanel().treeComboBox.getSelectedItem();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)delete.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File file = (File)node.getUserObject();

		try {
			View.engine.delete(drive, file);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e1) {
			System.out.format("Delete OnClick: %s\n", e1);
		}
	}

}
