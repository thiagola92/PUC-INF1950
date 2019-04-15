package view.frame.panel.buttonspanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import engine.drive.file.File;
import view.View;

public class OnClick implements ActionListener {
	
	private Delete delete;
	
	public OnClick(Delete delete) {
		this.delete = delete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)delete.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File file = (File) node.getUserObject();

			View.engine.delete(file);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e1) {
			System.out.format("Delete OnClick: %s\n", e1);
		}
	}

}
