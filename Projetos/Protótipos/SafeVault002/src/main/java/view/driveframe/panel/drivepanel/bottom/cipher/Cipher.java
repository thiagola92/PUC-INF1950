package view.driveframe.panel.drivepanel.bottom.cipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.file.File;
import view.driveframe.panel.drivepanel.bottom.Bottom;

@SuppressWarnings("serial")
public class Cipher extends JButton {
	
	private Bottom bottom;
	
	public Cipher(Bottom bottom) {
		this.bottom = bottom;
		
		icon();
		
		this.addActionListener((event) -> click());
	}
	
	private void icon() {
		String path = "lock.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null)
			this.setIcon(new ImageIcon(imgURL, "Cifrar"));
	    else
			this.setText("C");
	}
	
	private File file() {
		DefaultMutableTreeNode node = null;
		File file = null;
		
		try {
			node = (DefaultMutableTreeNode) bottom.drive_panel().center().tree().getSelectionPath().getLastPathComponent();
			file = (File) node.getUserObject();
		} catch(Exception e) {
			
			String message = Main.view.utilities().strings().utf8("Nenhum arquivo selecionado.");
	        JOptionPane.showMessageDialog(bottom.drive_panel().panel(), message);
	        
			e.printStackTrace();
		}
		
		return file;
	}
	
	private boolean can_cipher(File file) {
		if(!file.drive().utilities().vaults().is_secure(file))
			return true;
		
		String message = Main.view.utilities().strings().utf8("Não é permitido cifrar dentro do vault.");
        JOptionPane.showMessageDialog(bottom.drive_panel().panel(), message);
		
		return false;
	}
	
	private void cipher(File file) {
		String message = Main.view.utilities().strings().utf8("Cifrar utilizando esse botão é perigoso, a maneira segura seria mover o arquivo para a pasta segura." + "\n" + "Deseja cifrar mesmo assim? Note que o nome do arquivo não será alterado.");
		int answer = JOptionPane.showOptionDialog(bottom.drive_panel().panel(), message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return;
		
		try {
			bottom.drive_panel().panel().drive_frame().view().engine().actions().cipher().file(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void click() {
		File file = file();
		
		if(file == null)
			return;
		
		if(!can_cipher(file))
			return;
		
		cipher(file);
	}

}
