package view.settingsframe;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
	
	private SettingsFrame settings_frame;
	
	public OnChange(SettingsFrame settings_frame) {
		this.settings_frame = settings_frame;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		settings_frame.drive_frame().setEnabled(true);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		settings_frame.drive_frame().setEnabled(false);
	}

}
