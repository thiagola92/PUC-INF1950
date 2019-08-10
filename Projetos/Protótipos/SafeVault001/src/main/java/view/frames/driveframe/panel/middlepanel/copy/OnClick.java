package view.frames.driveframe.panel.middlepanel.copy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.api.client.http.HttpResponseException;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.frames.loadingframe.LoadingFrame;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Copy copy;
	
	public OnClick(Copy copy) {
		this.copy = copy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tree tree;
		
		tree = copy.fromDrivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode fromNode = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File fromFile = (File) fromNode.getUserObject();

		tree = copy.toDrivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode toNode = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File toFile = (File) toNode.getUserObject();
		
		View.loadingFrame = new LoadingFrame();
		
		try {
			Engine.copy(fromFile, toFile);
			
			View.update.updateListeners(UpdateOptions.FILE_UPDATED);
		} catch (HttpResponseException e1) {
			System.out.println("- Empty file?");
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			View.loadingFrame.dispose();
		}
	}

}
