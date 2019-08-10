package plugin;

import plugin._local.Local;
import plugin.googledrive.GoogleDrive;

public class Plugins {
	
	public Plugins() {
		
	}
	
	public Plugin local() {
		return new Local();
	}
	
	public Plugin googleDrive(String tokenName) throws Exception {
		return new GoogleDrive(tokenName);
	}
	
	public Plugin googleDrive() throws Exception {
		return new GoogleDrive("null");
	}

}
