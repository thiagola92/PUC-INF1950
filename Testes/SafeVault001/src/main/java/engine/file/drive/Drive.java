package engine.file.drive;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private String pluginName;
	private String startPath;
	
	private Plugin plugin;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.pluginName = pluginName;
		this.startPath = "";
		
		this.plugin = PluginList.createPlugin(pluginName);
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
	
	public String getStartPath() {
		return startPath;
	}
	
	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	public String toString() {
		return name;
	}
	
}
