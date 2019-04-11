package view.frame.panel.driverpanel.treecombobox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.driver.Drive;
import view.View;
import view.frame.panel.driverpanel.treescrollpane.TreeScrollPane;

public class OnChange implements ActionListener {
	
	private TreeComboBox treeComboBox;
	
	public OnChange(TreeComboBox treeComboBox) {
		this.treeComboBox = treeComboBox;
	}
	
	private TreeScrollPane getTreeScrollPane() {
		if(View.frame.panel.firstDriverPanel.treeComboBox == treeComboBox)
			return View.frame.panel.firstDriverPanel.treeScrollPane;
		
		return View.frame.panel.secondDriverPanel.treeScrollPane;
	}
	
	private Drive getDrive() {
		return (Drive)treeComboBox.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreeScrollPane treeScrollPane = getTreeScrollPane();
		treeScrollPane.resetRoot();
		treeScrollPane.add(getDrive(), "");
	}

}
