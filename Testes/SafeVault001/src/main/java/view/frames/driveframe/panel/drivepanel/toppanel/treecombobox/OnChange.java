package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OnChange implements ItemListener {
	
	public TreeComboBox treeComboBox;
	
	public OnChange(TreeComboBox treeComboBox) {
		this.treeComboBox = treeComboBox;
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {	
		if(treeComboBox.getItemCount() == 0)
			return;
		
		if(itemEvent.getStateChange() == ItemEvent.SELECTED)
			treeComboBox.driveSeleceted();
	}
}
