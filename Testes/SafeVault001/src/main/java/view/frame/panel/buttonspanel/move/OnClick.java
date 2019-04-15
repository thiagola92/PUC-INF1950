package view.frame.panel.buttonspanel.move;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.api.client.http.HttpResponseException;

import engine.Engine;
import engine.drive.file.File;

public class OnClick implements ActionListener {
	
	private Move move;
	
	public OnClick(Move move) {
		this.move = move;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DefaultMutableTreeNode fromNode = (DefaultMutableTreeNode)move.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File fromFile = (File) fromNode.getUserObject();
	
			DefaultMutableTreeNode toNode = (DefaultMutableTreeNode)move.toDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File toFile = (File) toNode.getUserObject();

			Engine.move(fromFile, toFile);
			
			move.fromDrivePanel().treeScrollPane.tree.updateRoot();
			move.toDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (HttpResponseException e1) {
			System.out.println("- Empty file?");
			System.out.println(e1);
		} catch (Exception e1) {
			System.out.format("Move OnClick: %s\n", e1);
		}
	}

}
