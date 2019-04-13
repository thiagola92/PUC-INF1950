package view.frame.panel.drivepanel.treescrollpane.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import engine.drive.file.File;

@SuppressWarnings("serial")
public class CellRenderer extends DefaultTreeCellRenderer {
	
	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        File driveFile = (File) node.getUserObject();
        
        if(driveFile.getType().equals("folder"))
        	this.setIcon(UIManager.getIcon("Tree.openIcon"));
        else
        	this.setIcon(UIManager.getIcon("Tree.leafIcon"));

        return this;
    }

}
