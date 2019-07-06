package view.frames.driveframe.panel.drivepanel.bottompanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.frames.loadingframe.LoadingFrame;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Delete delete;
	
	public OnClick(Delete delete) {
		this.delete = delete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tree tree = delete.bottomPanel.drivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();

		String message = "<html><font color='red' style='text-align: center'>";
		message += "Essa ação não é reversivel!<br>";
		message += "NÃO existe garantia que vá para a lixeira<br>";
		message += "Deseja continuar?<br>";
		message += "</font></html>";
		
		message = new String(message.getBytes(), StandardCharsets.UTF_8);
		
		int answer = JOptionPane.showOptionDialog(View.driveFrame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		View.loadingFrame = new LoadingFrame();
		
		try {
			Engine.delete(file);
			
			View.update.updateListeners(UpdateOptions.FILE_UPDATED);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			View.loadingFrame.dispose();
		}
	}

}
