package view.frame.panel.drivepanel.treecombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.file.File;
import engine.file.drive.Drive;
import view.View;
import view.frame.panel.drivepanel.DrivePanel;
import view.frame.panel.drivepanel.treescrollpane.tree.Tree;

public class OnChange implements ActionListener {
	
	private TreeComboBox treeComboBox;
	
	public OnChange(TreeComboBox treeComboBox) {
		this.treeComboBox = treeComboBox;
	}
	
	private DrivePanel getDrivePanel() {
		if(View.frame.panel.firstDrivePanel.treeComboBox == treeComboBox)
			return View.frame.panel.firstDrivePanel;
		
		return View.frame.panel.secondDrivePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(getDrivePanel().treeComboBox.getItemCount() == 0)
			return;
		
		Tree tree = getDrivePanel().treeScrollPane.tree;
		Drive drive = (Drive)treeComboBox.getSelectedItem();		
		File file = new File(drive, drive.getStartPath(), "folder");
		
		tree.newRoot(file);
	}

}
