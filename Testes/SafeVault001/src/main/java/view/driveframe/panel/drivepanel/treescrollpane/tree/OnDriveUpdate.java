package view.driveframe.panel.drivepanel.treescrollpane.tree;

import engine.update.UpdateListener;
import engine.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	private Tree tree;
	
	public OnDriveUpdate(Tree tree) {
		this.tree = tree;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate != UpdateOptions.FILE_UPDATE)
			return;
		
		tree.updateRoot();
	}

}
