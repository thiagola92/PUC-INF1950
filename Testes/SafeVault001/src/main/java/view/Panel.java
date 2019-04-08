package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import view.panel.ButtonsPanel;
import view.panel.DriverPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public Panel() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 0);
		constraints.fill = GridBagConstraints.BOTH;
		
		this.add(new DriverPanel(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		
		this.add(new ButtonsPanel(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = this.getComponentCount();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 0, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		
		this.add(new DriverPanel(), constraints);
	}
}
