package main;

import interfaceConsole.InterfaceConsole;
import plugin.Plugin;
import pluginGoogleDrive.PluginGoogleDrive;

public class Main {
	public static Plugin plugin;

	public static void main(String[] args) throws Exception {
		plugin = new PluginGoogleDrive();
		InterfaceConsole interfaceConsole = new InterfaceConsole();
		interfaceConsole.run();
	}
}
