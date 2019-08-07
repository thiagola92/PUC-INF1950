package engine.drives.drive;

import engine.channel.Channel;
import engine.drives.drive.file.File;
import engine.drives.drive.utilities.Utilities;
import engine.drives.drive.vault.Vault;
import plugin.Plugin;

public class Drive extends Channel {
	
	private String name;
	private String path;
	private Plugin plugin;
	
	private Vault vault;
	
	private Utilities utilities;
	
	public Drive(String name, Plugin plugin) {
		this.name = name;
		this.path = "/";
		this.plugin = plugin;
		this.vault = new Vault(this);

		utilities = new Utilities();
	}
	
	public String name() {
		return name;
	}
	
	public void set_name(String name) {
		this.name = name;
		
		this.notify("update");
	}
	
	public String path() {
		return path;
	}
	
	public void set_path(String path) {
		this.path = path;
		
		this.notify("update");
	}
	
	public Plugin plugin() {
		return plugin;
	}
	
	public Vault vault() {
		return vault;
	}
	
	public Utilities utilities() {
		return utilities;
	}
	
	public void create_vault() throws Exception {
		vault.create_vault();
		
		this.notify("create vault");
	}
	
	public void remove_vault() {
		// todo
		
		this.notify("remove vault");
	}
	
	public File start() {
		return new File(this, path, "folder");
	}
	
	public String toString() {
		return plugin.name() + ": " + name;
	}

}
