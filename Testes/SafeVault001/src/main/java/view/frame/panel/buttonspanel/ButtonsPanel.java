package view.frame.panel.buttonspanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.frame.panel.driverpanel.DriverPanel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	
	DriverPanel firstDriverPanel;
	DriverPanel secondDriverPanel;
	
	public ButtonsPanel(DriverPanel firstDriverPanel, DriverPanel secondDriverPanel) {
		this.firstDriverPanel = firstDriverPanel;
		this.secondDriverPanel = secondDriverPanel;
		
		this.setLayout(new GridBagLayout());

		addComponent(new JLabel("move"));
		addComponent(new JButton(">"));
		addComponent(new JButton("<"));
		
		addComponent(new JLabel("copy"));
		addComponent(new JButton(">"));
		addComponent(new JButton("<"));
	}
	
	private void addComponent(Component component) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = this.getComponentCount();
		constraints.insets = new Insets(10, 10, 0, 10);
		
		this.add(component, constraints);
	}

}
