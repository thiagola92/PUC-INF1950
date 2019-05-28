package view.driveframe.panel.drivepanel.toppanel.treecombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.file.File;
import engine.file.drive.Drive;
import view.View;
import view.driveframe.panel.drivepanel.DrivePanel;
import view.driveframe.panel.drivepanel.treescrollpane.tree.Tree;

public class OnChange implements ActionListener {
	
	private TreeComboBox treeComboBox;
	
	public OnChange(TreeComboBox treeComboBox) {
		this.treeComboBox = treeComboBox;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.driveFrame.panel.firstDrivePanel.topPanel.treeComboBox == treeComboBox)
			return View.driveFrame.panel.firstDrivePanel;
		
		return View.driveFrame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(getDrivePanel().topPanel.treeComboBox.getItemCount() == 0)
			return;
		
		Tree tree = getDrivePanel().treeScrollPane.tree;
		Drive drive = (Drive)treeComboBox.getSelectedItem();		
		File file = new File(drive, drive.getStartPath(), "folder");
		
		tree.newRoot(file);
	}

}
