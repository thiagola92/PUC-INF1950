package view.frames.driveframe.panel.drivepanel.treescrollpane.tree;

import view.update.UpdateListener;
import view.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	private Tree tree;
	
	public OnDriveUpdate(Tree tree) {
		this.tree = tree;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate != UpdateOptions.FILE_UPDATE)
			return;
		
		try {
			tree.updateRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
