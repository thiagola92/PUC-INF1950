package view.frame.driveframe.panel.drivepanel.bottompanel.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frame.driveframe.panel.drivepanel.DrivePanel;
import view.update.UpdateOptions;

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

		String message = "<html><font color='red' style='text-align: center'>";
		message += "Essa a&ccedil;&atilde;o n&atilde;o &eacute; revers&iacute;vel!<br>";
		message += "N&Atilde;O existe garantia que v&aacute; para a lixeira<br>";
		message += "Deseja continuar<br>";
		message += "</font></html>";
		
		int answer = JOptionPane.showOptionDialog(View.driveFrame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			Engine.delete(file);
			
			View.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
