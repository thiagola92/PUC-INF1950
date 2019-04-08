package view.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	
	public ButtonsPanel() {
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
