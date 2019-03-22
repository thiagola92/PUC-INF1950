package plugin;

import java.util.ArrayList;

import pluginDefault.PluginDefault;
import pluginGoogleDrive.PluginGoogleDrive;

public class Plugin implements PluginInterface {
	
	private PluginInterface plugin;
	
	public Plugin(String pluginName) throws Exception {
		if(pluginName == null)
			plugin = new PluginDefault();
		else if(pluginName.toLowerCase().equals("googledrive"))
			plugin = new PluginGoogleDrive();
		else
			throw new Exception("The plugin \"" + pluginName + "\" is not on the list");
	}
	
	
	public void createFolder(String path) throws Exception {
		plugin.createFolder(path);
	}
	
	public ArrayList<String[]> listFolder(String path) throws Exception {
		return plugin.listFolder(path);
	}
	
	public void deleteFolder(String path) throws Exception {
		plugin.deleteFolder(path);
	}
	
	public void createFile(String path) throws Exception {
		plugin.createFile(path);
	}
	
	public byte[] readFile(String path) throws Exception {
		return plugin.readFile(path);
	}
	
	public void writeFile(String path, byte[] content) throws Exception {
		plugin.writeFile(path, content);
	}
	
	public void deleteFile(String path) throws Exception {
		plugin.deleteFile(path);
	}

}
