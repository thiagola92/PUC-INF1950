package plugin;

import pluginDefault.PluginDefault;
import pluginGoogleDrive.PluginGoogleDrive;

public class PluginsList {
	
	public static Plugin getPlugin(String pluginName) throws Exception {
		if(pluginName == null)
			return new PluginDefault();
		else if(pluginName.toLowerCase().equals("googledrive"))
			return new PluginGoogleDrive();
		
		throw new Exception("The plugin \"" + pluginName + "\" is not on the list");
	}

}
