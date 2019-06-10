package view.frames.driveframe.menubar.transfer.globaltoglobal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;
import view.frames.driveframe.panel.drivepanel._enumeration.mode.DrivePanelMode;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		View.driveFrame.panel.leftDrivePanel.drivePanelMode = DrivePanelMode.GLOBAL_MODE;
		View.driveFrame.panel.rightDrivePanel.drivePanelMode = DrivePanelMode.GLOBAL_MODE;
		
		View.update.updateListeners(UpdateOptions.DRIVE_UPDATE);
	}

}
