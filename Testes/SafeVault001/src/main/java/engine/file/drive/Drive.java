package engine.file.drive;

import java.security.PrivateKey;
import java.security.PublicKey;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private String pluginName;
	private String startPath;
	
	private Plugin plugin;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.pluginName = pluginName;
		this.startPath = "";
		
		this.plugin = PluginList.createPlugin(pluginName);
		this.publicKey = null;
		this.privateKey = null;
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
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	public String toString() {
		return name;
	}
	
}
