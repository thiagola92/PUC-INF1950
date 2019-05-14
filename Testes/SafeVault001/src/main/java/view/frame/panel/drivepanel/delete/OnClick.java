package view.frame.panel.drivepanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import view.View;

public class OnClick implements ActionListener {
	
	private Delete delete;
	
	public OnClick(Delete delete) {
		this.delete = delete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)delete.fromDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();
		
		try {
			Engine.delete(file, false);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (DirectoryNotEmptyException e1) {
			deleteRecursive(file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void deleteRecursive(File file) {
		String message = "Esta pasta possui arquivos dentro, deseja deletar tudo dentro?";
		int answer = JOptionPane.showOptionDialog(View.frame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			Engine.delete(file, true);
			
			delete.fromDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
