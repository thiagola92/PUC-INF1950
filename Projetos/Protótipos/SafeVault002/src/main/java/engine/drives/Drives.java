package engine.drives;

import java.util.ArrayList;
import java.util.stream.Stream;

import engine.channel.Channel;
import engine.drives.drive.Drive;
import plugin.Plugin;

public class Drives extends Channel {
	
	private ArrayList<Drive> drives;
	
	public Drives() {
		drives = new ArrayList<Drive>();
	}
	
	public Stream<Drive> get() {
		return drives.stream();
	}
	
	public Drive get(String name) {
		return drives.stream().filter((drive) -> drive.name().equals(name)).findFirst().orElse(null);
	}
	
	public Drive create(String name, Plugin plugin) {
		if(get(name) != null)
			return null;
		
		Drive drive = new Drive(name, plugin);
		drives.add(drive);
		
		this.notify("create");
		drive.subscribe((message) -> notification(message));
		
		return drive;
	}
	
	public void remove(String name) {
		drives.remove(get(name));
		
		this.notify("remove");
	}
	
	private void notification(String message) {
		this.notify(message);
	}

}
