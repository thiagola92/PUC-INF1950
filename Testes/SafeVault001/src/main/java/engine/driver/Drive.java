package engine.driver;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private Plugin plugin;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.plugin = PluginList.createPlugin(pluginName);
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
	
	public String toString() {
		return name;
	}
	
}
