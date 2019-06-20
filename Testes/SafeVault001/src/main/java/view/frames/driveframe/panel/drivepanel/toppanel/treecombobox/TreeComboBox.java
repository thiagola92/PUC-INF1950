package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import engine.file.File;
import engine.file.drive.Drive;
import engine.file.vault.Vault;
import engine.file.vault.index.exception.IndexNotFoundException;
import view.View;
import view.frames.driveframe.panel.drivepanel._enumeration.mode.DrivePanelMode;
import view.frames.driveframe.panel.drivepanel.toppanel.TopPanel;
import view.stringformat.StringFormat;

@SuppressWarnings({ "serial" })
public class TreeComboBox extends JComboBox<Drive>{
	
	public TopPanel topPanel;
	
	public TreeComboBox(TopPanel topPanel) {
		this.topPanel = topPanel;
		
		this.addItemListener(new OnChange(this));
		
		View.update.addUpdateListener(new OnDriveUpdate(this));
	}
	
	public void driveSeleceted() {
		Drive drive = (Drive)this.getSelectedItem();		
		File file = new File(drive, drive.getStartPath(), "folder");
		File vault = getVault(file);
		
		if(topPanel.drivePanel.drivePanelMode == DrivePanelMode.VAULT_MODE)
			file = vault;
		
		if(file == null)
			cleanTree(drive);
		else
			updateTree(file);
	}
	
	public File getVault(File root) {		
		try {
			if(Vault.existVault(root))
				return Vault.getVault(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void cleanTree(Drive drive) {
		StringFormat message = new StringFormat("O drive '" + drive.getName() + "' não possui um vault.");
		JOptionPane.showMessageDialog(View.driveFrame, message);

		topPanel.drivePanel.treeScrollPane.tree.cleanChildren();
	}
	
	public void updateTree(File file) {
		try {
			topPanel.drivePanel.treeScrollPane.tree.newRoot(file);
		} catch (BadPaddingException | InvalidKeyException e1) {
			StringFormat message = new StringFormat("O vault '" + file.getDrive().getName() + "' não esta ligado a sua chave privada/publica.");
			JOptionPane.showMessageDialog(View.driveFrame, message);

			topPanel.drivePanel.treeScrollPane.tree.cleanChildren();
		} catch (IndexNotFoundException e1) {
			StringFormat message = new StringFormat("Não foi possível encontrar o index do vault '" + file.getDrive().getName() + "'.");
			JOptionPane.showMessageDialog(View.driveFrame, message);

			topPanel.drivePanel.treeScrollPane.tree.cleanChildren();
		} catch (ArrayIndexOutOfBoundsException e1) {
			StringFormat message = new StringFormat("Index do drive '" + file.getDrive().getName() + "' está mal formulado ou não é o index do vault.");
			JOptionPane.showMessageDialog(View.driveFrame, message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
}
