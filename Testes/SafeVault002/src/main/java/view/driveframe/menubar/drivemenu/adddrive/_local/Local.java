package view.driveframe.menubar.drivemenu.adddrive._local;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import _main.Main;
import engine.drives.drive.Drive;
import plugin.Plugin;

@SuppressWarnings("serial")
public class Local extends JMenuItem {

	public Local() {
		this.setText("Local");
		
		this.addActionListener((action) -> click());
	}
	
	private Plugin plugin(String name) {
		try {
			return Main.view.engine().plugins().local();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String path() {
		JFileChooser popup = new JFileChooser();
		popup.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if(popup.showOpenDialog(Main.view.drive_frame()) != JFileChooser.APPROVE_OPTION)
			return null;
		
		return popup.getSelectedFile().getPath();
	}
	
	private Drive create(String name, Plugin plugin) {
		try {
			return Main.view.engine().drives().create(name, plugin);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void click() {
		String name = JOptionPane.showInputDialog("Volume name:");
		
		if(name == null)
			return;
		
		String path = path();
		
		if(path == null)
			return;
		
		Plugin plugin = plugin(name);
		
		if(plugin == null)
			return;
		
		create(name, plugin).set_path(path);
	}
}
