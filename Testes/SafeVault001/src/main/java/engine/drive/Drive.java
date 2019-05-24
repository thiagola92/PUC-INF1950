package engine.drive;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private String pluginName;
	private Plugin plugin;
	private String startPath;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.pluginName = pluginName;
		this.plugin = PluginList.createPlugin(pluginName);
		this.startPath = "";
	}
	
	public Drive(String driveName, String startPath, String pluginName) throws Exception {
		this.name = driveName;
		this.plugin = PluginList.createPlugin(pluginName);
		this.startPath = startPath;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPluginName() {
		return pluginName;
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	public String getPath() {
		return startPath;
	}
	
	public void setPath(String path) {
		this.startPath = path;
	}
	
	public String toString() {
		return name;
	}
	
}
