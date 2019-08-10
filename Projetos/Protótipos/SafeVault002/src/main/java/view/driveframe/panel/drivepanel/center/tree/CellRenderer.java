package view.driveframe.panel.drivepanel.center.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import engine.drives.drive.file.File;

@SuppressWarnings("serial")
public class CellRenderer extends DefaultTreeCellRenderer {
	
	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        File file = (File) node.getUserObject();
        
        if(file == null)
        	return this;
        else if(file.type().equals("folder"))
        	this.setIcon(UIManager.getIcon("Tree.openIcon"));
        else if(file.type().equals("file"))
        	this.setIcon(UIManager.getIcon("Tree.leafIcon"));

        return this;
    }

}
