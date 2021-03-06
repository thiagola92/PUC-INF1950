package view.update;

import java.util.ArrayList;

public class Update {
	
	private ArrayList<UpdateListener> updateListenerList = new ArrayList<UpdateListener>();
	
	public void addUpdateListener(UpdateListener updateListener) {
		boolean exist = updateListenerList.stream().anyMatch((listener) -> {
			return listener.equals(updateListener);
		});
		
		if(exist)
			return;

		updateListenerList.add(updateListener);
	}
	
	public void removeUpdateListener(UpdateListener listener) {
		updateListenerList.remove(listener);
	}
	
	public void updateListeners(UpdateOptions updateOption) {
		for(int i = 0; i < updateListenerList.size(); i++)
			updateListenerList.get(i).engineUpdated(updateOption);
	}
}
