package view.driveframe.panel.middlepanel.move;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.api.client.http.HttpResponseException;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Move move;
	
	public OnClick(Move move) {
		this.move = move;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		DefaultMutableTreeNode fromNode = (DefaultMutableTreeNode)move.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File fromFile = (File) fromNode.getUserObject();

		DefaultMutableTreeNode toNode = (DefaultMutableTreeNode)move.toDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File toFile = (File) toNode.getUserObject();
		
		try {
			Engine.move(fromFile, toFile);
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (HttpResponseException e1) {
			System.out.println("- Empty file?");
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
