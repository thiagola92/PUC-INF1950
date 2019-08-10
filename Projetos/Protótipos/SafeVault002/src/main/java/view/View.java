package view;

import engine.Engine;
import view.driveframe.DriveFrame;
import view.utilities.Utilities;

public class View {

	private Engine engine;
	
	private Utilities utilities;
	private DriveFrame drive_frame;
	
	public View() {
		engine = new Engine();
		
		utilities = new Utilities(this);
		drive_frame = new DriveFrame(this);
	}
	
	public Engine engine() {
		return engine;
	}
	
	public Utilities utilities() {
		return utilities;
	}
	
	public DriveFrame drive_frame() {
		return drive_frame;
	}

}
