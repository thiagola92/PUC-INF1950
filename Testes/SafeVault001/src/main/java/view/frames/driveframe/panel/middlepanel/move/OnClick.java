package view.frames.driveframe.panel.middlepanel.move;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import com.google.api.client.http.HttpResponseException;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Move move;
	
	public OnClick(Move move) {
		this.move = move;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tree tree;
		
		tree = move.fromDrivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode fromNode = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File fromFile = (File) fromNode.getUserObject();

		tree = move.toDrivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode toNode = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File toFile = (File) toNode.getUserObject();
		
		try {
			Engine.move(fromFile, toFile);
			
			View.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (HttpResponseException e1) {
            JOptionPane.showMessageDialog(View.driveFrame, "Arquivo vazio?");
            
			e1.printStackTrace();
		} catch (FileAlreadyExistsException e1) {
            JOptionPane.showMessageDialog(View.driveFrame, "Arquivo/Pasta já existe.");
            
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
