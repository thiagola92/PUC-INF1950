package view.frames.loadingframe.panel;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
	public JLabel loadingLabel = new JLabel();
	
	public Panel() {
		this.setLayout(new GridLayout());
		
//		this.loadingLabel.setText("<html><font color=\"red\">LOADING...</font></html>");
		this.loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		this.add(loadingLabel, constraints);
	}

}
