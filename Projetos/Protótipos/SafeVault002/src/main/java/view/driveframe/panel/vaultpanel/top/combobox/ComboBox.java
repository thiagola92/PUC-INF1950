package view.driveframe.panel.vaultpanel.top.combobox;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

import _main.Main;
import engine.drives.drive.Drive;
import view.driveframe.panel.vaultpanel.top.Top;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox<Drive>{
	
	private Top top;
	
	public ComboBox(Top top) {
		this.top = top;
		
		this.setPreferredSize(new Dimension(250, 25));
		this.addItemListener((event) -> change(event));
		
		top.vault_panel().panel().drive_frame().view().engine().drives().subscribe((message) -> notification(message));
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
			top.vault_panel().center().tree().drive((Drive)this.getSelectedItem());
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
				.filter((drive) -> drive.vault().folder() != null)
				.findFirst()
				.orElse(null);
	}
	
	private Drive removed() {
		return items()
				.stream()
				.filter((item) -> top.vault_panel()
									.panel()
									.drive_frame()
									.view()
									.engine()
									.drives()
									.get()
									.anyMatch((drive) -> drive == item))
				.filter((drive) -> drive.vault().folder() == null)
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
			top.vault_panel().center().tree().clean();
	}
	
	private void update() {		
		if((Drive)this.getSelectedItem() == null)
			return;
		
		try {
			top.vault_panel().center().tree().drive((Drive)this.getSelectedItem());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void notification(String message) {
		if(message.equals("create vault"))
			create();
		else if(message.equals("destroy vault"))
			remove();
		else if(message.equals("update vault"))
			update();
	}

}
