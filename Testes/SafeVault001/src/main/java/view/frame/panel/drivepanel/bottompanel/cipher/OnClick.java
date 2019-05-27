package view.frame.panel.drivepanel.bottompanel.cipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;
import view.View;
import view.frame.panel.drivepanel.DrivePanel;

public class OnClick implements ActionListener {
	
	private Cipher cipher;
	
	public OnClick(Cipher cipher) {
		this.cipher = cipher;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.frame.panel.firstDrivePanel.bottomPanel.cipher == cipher)
			return View.frame.panel.firstDrivePanel;
		
		return View.frame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();

		String message = "Cifrar utilizando esse botão é perigoso, a maneira segura seria mover o arquivo para a pasta segura.\nDeseja cifrar mesmo assim? Note que o nome do arquivo não será alterado.";
		int answer = JOptionPane.showOptionDialog(View.frame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			Engine.cipher(file);
			
            JOptionPane.showMessageDialog(View.frame, "Cifrado com sucesso.");
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
