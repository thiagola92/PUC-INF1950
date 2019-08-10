package view.driveframe.panel.drivepanel.top.combobox;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

import _main.Main;
import engine.drives.drive.Drive;
import view.driveframe.panel.drivepanel.top.Top;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox<Drive>{
	
	private Top top;
	
	public ComboBox(Top top) {
		this.top = top;
		
		this.setPreferredSize(new Dimension(250, 25));
		this.addItemListener((event) -> change(event));
		
		top.drive_panel().panel().drive_frame().view().engine().drives().subscribe((message) -> notification(message));
	}
	
	public Top top() {
		return top;
	}
	
	private void change(ItemEvent event) {
		if(this.getItemCount() == 0)
			return;
		
		if(event.getStateChange() != ItemEvent.SELECTED)
			return;
		
		try {
			top.drive_panel().center().tree().drive((Drive)this.getSelectedItem());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Drive> items() {
		ArrayList<Drive> items = new ArrayList<Drive>();
		
		for(int i = 0; i < this.getItemCount(); i++)
			items.add(this.getItemAt(i));
		
		return items;
	}
	
	private Drive added() {
		return Main.view.engine()
				.drives()
				.get()
				.filter((drive) -> !items()
						.stream()
						.anyMatch((item) -> item == drive))
				.findFirst()
				.orElse(null);
	}
	
	private Drive removed() {
		return items()
				.stream()
				.filter((item) -> !Main.view.engine()
									.drives()
									.get()
									.anyMatch((drive) -> drive == item))
				.findFirst()
				.orElse(null);
	}
	
	private void create() {
		Drive added = added();
		
		if(added == null)
			return;
		
		this.addItem(added);
	}
	
	private void remove() {
		Drive removed = removed();
		
		if(removed == null)
			return;
		
		this.removeItem(removed);
		
		if(this.getItemCount() == 0)
			top.drive_panel().center().tree().clean();
	}
	
	private void update() {
		try {
			top.drive_panel().center().tree().drive((Drive)this.getSelectedItem());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void notification(String message) {
		if(message.equals("create"))
			create();
		else if(message.equals("remove"))
			remove();
		else if(message.equals("update"))
			update();
		else if(message.equals("create vault"));
			update();
	}

}
