package view.driveframe.panel.drivepanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import view.driveframe.panel.Panel;
import view.driveframe.panel.drivepanel.bottom.Bottom;
import view.driveframe.panel.drivepanel.center.Center;
import view.driveframe.panel.drivepanel.top.Top;

@SuppressWarnings("serial")
public class DrivePanel extends JPanel {
	
	private Panel panel;
	
	private Top top;
	private Center center;
	private Bottom bottom;
	
	public DrivePanel(Panel panel) {
		this.panel = panel;
		
		top = new Top(this);
		center = new Center(this);
		bottom = new Bottom(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(top, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(center, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(bottom, constraints);
	}
	
	public Panel panel() {
		return panel;
	}
	
	public Top top() {
		return top;
	}
	
	public Center center() {
		return center;
	}
	
	public Bottom bottom() {
		return bottom;
	}

}
