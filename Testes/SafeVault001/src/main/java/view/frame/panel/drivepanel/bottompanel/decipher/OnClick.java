package view.frame.panel.drivepanel.bottompanel.decipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.BadPaddingException;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.update.UpdateOptions;
import view.View;
import view.frame.panel.drivepanel.DrivePanel;

public class OnClick implements ActionListener {
	
	private Decipher decipher;
	
	public OnClick(Decipher decipher) {
		this.decipher = decipher;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.frame.panel.firstDrivePanel.bottomPanel.decipher == decipher)
			return View.frame.panel.firstDrivePanel;
		
		return View.frame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)getDrivePanel().treeScrollPane.tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();
		
		try {
			Engine.decipher(file);
			
            JOptionPane.showMessageDialog(View.frame, "Decifrado com sucesso.");
			
			Engine.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (BadPaddingException e1) {
            JOptionPane.showMessageDialog(View.frame, "Falha ao tentar decifrar.");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
