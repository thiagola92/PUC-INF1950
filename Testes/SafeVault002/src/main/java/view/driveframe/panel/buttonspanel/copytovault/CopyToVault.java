package view.driveframe.panel.buttonspanel.copytovault;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.file.File;
import view.driveframe.panel.buttonspanel.ButtonsPanel;

@SuppressWarnings("serial")
public class CopyToVault extends JButton {
	
	private ButtonsPanel buttons_panel;
	
	public CopyToVault(ButtonsPanel buttons_panel) {
		this.buttons_panel = buttons_panel;
		
		icon();
		
		this.addActionListener((event) -> click());
	}
	
	public ButtonsPanel buttons_panel() {
		return buttons_panel;
	}
	
	private void icon() {
		String path = "angle-double-right.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null)
			this.setIcon(new ImageIcon(imgURL, "Copiar para o vault"));
	    else
    		this.setText(">>");
	}
	
	private File file() {
		DefaultMutableTreeNode node = null;
		File file = null;
		
		try {
			node = (DefaultMutableTreeNode) buttons_panel.panel().drive_panel().center().tree().getSelectionPath().getLastPathComponent();
			file = (File) node.getUserObject();
		} catch(Exception e) {
			
			String message = Main.view.utilities().strings().utf8("Nenhum arquivo selecionado para copiar.");
	        JOptionPane.showMessageDialog(buttons_panel.panel(), message);
	        
			e.printStackTrace();
		}
		
		return file;
	}
	
	private File folder() {
		DefaultMutableTreeNode node = null;
		File folder = null;
		
		try {
			node = (DefaultMutableTreeNode) buttons_panel.panel().vault_panel().center().tree().getSelectionPath().getLastPathComponent();
			folder = (File) node.getUserObject();
		} catch(Exception e) {
			
			String message = Main.view.utilities().strings().utf8("Nenhuma pasta selecionado para qual copiar o arquivo.");
	        JOptionPane.showMessageDialog(buttons_panel.panel(), message);
	        
			e.printStackTrace();
		}
		
		return folder;
	}
	
	private void copy(File file, File folder) {
		try {
			buttons_panel.panel().drive_frame().view().engine().actions().copy().file(file, folder);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update_trees() {
		try {
			buttons_panel.panel().drive_panel().center().tree().update();
			buttons_panel.panel().vault_panel().center().tree().update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void click() {
		File file = file();
		
		if(file == null)
			return;
		
		File folder = folder();
		
		if(folder == null)
			return;
		
		copy(file, folder);
		
		update_trees();
	}

}
