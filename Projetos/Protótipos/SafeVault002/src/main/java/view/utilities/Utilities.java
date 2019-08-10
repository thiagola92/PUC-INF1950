package view.utilities;

import view.View;
import view.utilities.strings.Strings;

public class Utilities {

	private View view;
	private Strings strings;
	
	public Utilities(View view) {
		this.view = view;
		
		strings = new Strings();
	}
	
	public View view() {
		return view;
	}
	
	public Strings strings() {
		return strings;
	}

}
