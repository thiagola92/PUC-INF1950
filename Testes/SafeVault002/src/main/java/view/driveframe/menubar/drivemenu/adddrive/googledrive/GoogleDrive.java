package view.driveframe.menubar.drivemenu.adddrive.googledrive;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import _main.Main;
import plugin.Plugin;

@SuppressWarnings("serial")
public class GoogleDrive extends JMenuItem {
	
	public GoogleDrive() {
		this.setText("Google Drive");
		
		this.addActionListener((action) -> click());
	}
	
	private Plugin plugin(String name) {
		try {
			return Main.view.engine().plugins().googleDrive(name);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void create(String name, Plugin plugin) {
		try {
			Main.view.engine().drives().create(name, plugin);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void click() {
		String name = JOptionPane.showInputDialog("Volume name:");
		
		if(name == null)
			return;
		
		Plugin plugin = plugin(name);
		
		if(plugin == null)
			return;
		
		create(name, plugin);
	}

}
