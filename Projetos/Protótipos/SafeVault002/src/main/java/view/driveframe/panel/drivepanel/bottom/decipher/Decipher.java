package view.driveframe.panel.drivepanel.bottom.decipher;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.file.File;
import view.driveframe.panel.drivepanel.bottom.Bottom;

@SuppressWarnings("serial")
public class Decipher extends JButton {
	
	private Bottom bottom;
	
	public Decipher(Bottom bottom) {
		this.bottom = bottom;
		
		icon();
		
		this.addActionListener((event) -> click());
	}
	
	private void icon() {
		String path = "unlock.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Decifrar"));
	    } else {
			this.setText("D");
	    }
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
	
	private boolean can_decipher(File file) {
		if(!file.drive().utilities().vaults().is_secure(file))
			return true;
		
		String message = Main.view.utilities().strings().utf8("Não é permitido decifrar dentro do vault.");
        JOptionPane.showMessageDialog(bottom.drive_panel().panel(), message);
		
		return false;
	}
	
	private void decipher(File file) {		
		try {
			bottom.drive_panel().panel().drive_frame().view().engine().actions().decipher().file(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void click() {
		File file = file();
		
		if(file == null)
			return;
		
		if(!can_decipher(file))
			return;
		
		decipher(file);
	}

}
