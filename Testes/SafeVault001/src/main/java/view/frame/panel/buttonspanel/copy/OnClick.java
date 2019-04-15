package view.frame.panel.buttonspanel.copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.api.client.http.HttpResponseException;

import engine.drive.file.File;
import view.View;

public class OnClick implements ActionListener {
	
	private Copy copy;
	
	public OnClick(Copy copy) {
		this.copy = copy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DefaultMutableTreeNode fromNode = (DefaultMutableTreeNode)copy.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File fromFile = (File) fromNode.getUserObject();
	
			DefaultMutableTreeNode toNode = (DefaultMutableTreeNode)copy.toDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File toFile = (File) toNode.getUserObject();

			View.engine.copy(fromFile, toFile);
			
			copy.fromDrivePanel().treeScrollPane.tree.updateRoot();
			copy.toDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (HttpResponseException e1) {
			System.out.println("- Empty file?");
			System.out.println(e1);
		} catch (Exception e1) {
			System.out.format("Copy OnClick: %s\n", e1);
		}
	}

}
