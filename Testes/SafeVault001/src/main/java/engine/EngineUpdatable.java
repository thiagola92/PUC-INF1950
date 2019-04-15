package engine;

import java.util.ArrayList;

public abstract class EngineUpdatable {
	
	private ArrayList<EngineListener> engineListenerList = new ArrayList<EngineListener>();
	
	public void addEngineListener(EngineListener engineListener) {
		boolean exist = engineListenerList.stream().anyMatch((listener) -> {
			return listener.equals(engineListener);
		});
		
		if(exist)
			return;

		engineListenerList.add(engineListener);
	}
	
	public void removeEngineListener(EngineListener engineListener) {
		engineListenerList.remove(engineListener);
	}
	
	public void updateEngineListeners() {
		engineListenerList.forEach(listener -> {
			listener.engineUpdated();
		});
	}
}
