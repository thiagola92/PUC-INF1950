package engine.drive;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private Plugin plugin;
	private String path;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.plugin = PluginList.createPlugin(pluginName);
		this.path = "";
	}
	
	public Drive(String driveName, String path, String pluginName) throws Exception {
		this.name = driveName;
		this.plugin = PluginList.createPlugin(pluginName);
		this.path = path;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString() {
		return name;
	}
	
}
