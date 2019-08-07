package engine;

import engine.actions.Actions;
import engine.drives.Drives;
import plugin.Plugins;

public class Engine {

	private Plugins plugins;
	private Drives drives;
	private Actions actions;
	
	public Engine() {
		plugins = new Plugins();
		drives = new Drives();
		actions = new Actions();
	}
	
	public Plugins plugins() {
		return plugins;
	}
	
	public Drives drives() {
		return drives;
	}
	
	public Actions actions() {
		return actions;
	}
}
