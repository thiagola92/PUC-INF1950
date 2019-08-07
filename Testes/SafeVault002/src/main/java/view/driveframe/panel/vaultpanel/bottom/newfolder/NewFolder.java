package view.driveframe.panel.vaultpanel.bottom.newfolder;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.file.File;
import view.driveframe.panel.vaultpanel.bottom.Bottom;

@SuppressWarnings("serial")
public class NewFolder extends JButton {
	
	private Bottom bottom;
	
	public NewFolder(Bottom bottom) {
		this.bottom = bottom;
		
		icon();
		
		this.addActionListener((event) -> click());
	}
	
	private void icon() {
		String path = "folder-plus.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Criar pasta"));
	    } else {
			this.setText("+");
	    }
	}
	
	private File file() {
		DefaultMutableTreeNode node = null;
		File file = null;
		
		try {
			node = (DefaultMutableTreeNode) bottom.vault_panel().center().tree().getSelectionPath().getLastPathComponent();
			file = (File) node.getUserObject();
		} catch(Exception e) {
			
			String message = Main.view.utilities().strings().utf8("Nenhum arquivo selecionado.");
	        JOptionPane.showMessageDialog(bottom.vault_panel().panel(), message);
	        
			e.printStackTrace();
		}
		
		return file;
	}
	
	private boolean is_folder(File file) {
		if(file.type().equals("folder"))
			return true;
		
		String message = Main.view.utilities().strings().utf8("Você selecionou um arquivo, não uma pasta.");
        JOptionPane.showMessageDialog(bottom.vault_panel().panel(), message);
		
        return false;
	}
	
	private void new_folder(File folder, String name) {
		try {
			bottom.vault_panel().panel().drive_frame().view().engine().actions().create().folder(folder, name);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update_tree() {
		try {
			bottom.vault_panel().center().tree().update();
			bottom.vault_panel().panel().drive_panel().center().tree().update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void click() {
		File folder = file();
		
		if(folder == null)
			return;
		
		if(!is_folder(folder))
			return;
		
		String name = JOptionPane.showInputDialog("Nome da pasta:");
		
		if(name == null)
			return;
		
		new_folder(folder, name);
		
		update_tree();
	}

}
