package view.frames.driveframe.panel.drivepanel.bottompanel.cipher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import engine.Engine;
import engine.file.File;
import engine.file.vault.Vault;
import view.View;
import view.frames.driveframe.panel.drivepanel.treescrollpane.tree.Tree;
import view.stringformat.StringFormat;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	public Cipher cipher;
	
	public OnClick(Cipher cipher) {
		this.cipher = cipher;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tree tree = cipher.bottomPanel.drivePanel.treeScrollPane.tree;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
		File file = (File) node.getUserObject();
		
		if(canCipherFile(file) == false)
			return;

		cipherFile(file);
	}
	
	public void cipherFile(File file) {
		StringFormat message = new StringFormat("Cifrar utilizando esse botão é perigoso, a maneira segura seria mover o arquivo para a pasta segura.\\nDeseja cifrar mesmo assim? Note que o nome do arquivo não será alterado.");
		int answer = JOptionPane.showOptionDialog(View.driveFrame, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			Engine.cipher(file);
			
            JOptionPane.showMessageDialog(View.driveFrame, "Cifrado com sucesso.");
			
            View.update.updateListeners(UpdateOptions.FILE_UPDATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean canCipherFile(File file) {
		if(Vault.isInsideVault(file) == false)
			return true;

		StringFormat message = new StringFormat("Não é permitido cifrar dentro do vault.");
        JOptionPane.showMessageDialog(View.driveFrame, message);
		
		return false;
	}

}
