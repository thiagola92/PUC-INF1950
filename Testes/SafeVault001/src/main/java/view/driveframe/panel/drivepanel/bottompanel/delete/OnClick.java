package view.driveframe.panel.drivepanel.bottompanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.DirectoryNotEmptyException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;
import view.View;
import view.driveframe.panel.drivepanel.DrivePanel;

public class OnClick implements ActionListener {
	
	private Delete delete;
	
	public OnClick(Delete delete) {
		this.delete = delete;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.driveFrame.panel.firstDrivePanel.bottomPanel.delete == delete)
			return View.driveFrame.panel.firstDrivePanel;
		
		return View.driveFrame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();
		
		try {
			Engine.delete(file, false);
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (DirectoryNotEmptyException e1) {
			deleteRecursive(file);
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void deleteRecursive(File file) {
		String message = "Esta pasta possui arquivos dentro, deseja deletar tudo dentro?";
		int answer = JOptionPane.showOptionDialog(View.driveFrame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			Engine.delete(file, true);
			
			getDrivePanel().treeScrollPane.tree.updateRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
