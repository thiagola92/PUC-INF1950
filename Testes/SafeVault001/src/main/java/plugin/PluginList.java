package plugin;

import plugin._default.Default;
import plugin.googledrive.GoogleDrive;

public class PluginList {
	
	public static Plugin createPlugin(String name) throws Exception {
		if(name == null)
			return new Default();
		else if(name.toLowerCase().equals("googledrive"))
			return new GoogleDrive();
		
		return null;
	}

}
