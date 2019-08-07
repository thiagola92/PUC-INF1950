package view.driveframe.panel.vaultpanel.bottom.delete;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import _main.Main;
import engine.drives.drive.file.File;
import view.driveframe.panel.vaultpanel.bottom.Bottom;

@SuppressWarnings("serial")
public class Delete extends JButton {
	
	private Bottom bottom;
	
	public Delete(Bottom bottom) {
		this.bottom = bottom;
		
		icon();
		
		this.addActionListener((event) -> click());
	}
	
	private void icon() {
		String path = "trash.png";
	    URL imgURL = getClass().getResource(path);
	    
	    if (imgURL != null) {
			this.setIcon(new ImageIcon(imgURL, "Deletar"));
	    } else {
			this.setText("X");
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
	
	private boolean should_continue() {

		String message = "<html><font color='red' style='text-align: center'>";
		message += "Essa ação não é reversivel!<br>";
		message += "Deseja continuar?<br>";
		message += "</font></html>";
		message = Main.view.utilities().strings().utf8(message);
		int answer = JOptionPane.showOptionDialog(bottom.vault_panel().panel(), message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);
		
		if(answer == JOptionPane.NO_OPTION)
			return false;
		
		 return true;
	}
	
	private void delete(File file) {
		try {
			bottom.vault_panel().panel().drive_frame().view().engine().actions().delete().file(file);
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
		File file = file();
		
		if(file == null)
			return;
		
		if(!should_continue())
			return;
		
		delete(file);
		
		update_tree();
	}

}
