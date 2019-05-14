package view.frame.panel.drivepanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;

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

			Engine.delete(file, false);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (DirectoryNotEmptyException e1) {
			deleteRecursive();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void deleteRecursive() {
		String message = "Esta pasta possui arquivos dentro, deseja deletar tudo dentro?";
		int answer = JOptionPane.showOptionDialog(delete, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)delete.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
			File file = (File) node.getUserObject();

			Engine.delete(file, true);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
