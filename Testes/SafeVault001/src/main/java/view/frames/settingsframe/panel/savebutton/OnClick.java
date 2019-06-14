package view.frames.settingsframe.panel.savebutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.swing.JOptionPane;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.settingsframe.panel.Panel;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Panel panel;
	
	public OnClick(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean changeKeys = false;
		
		PublicKey publicKey = panel.publicKey;
		PrivateKey privateKey = panel.privateKey;
		if(publicKey != null && privateKey != null) {
			if(isValidKeys(publicKey, privateKey) == false)
				return;
			
			changeKeys = true;
		}
		
		String newStartPath = panel.startPath.getText();
		if(isValidPath(newStartPath) == false)
			return;
		
		if(changeKeys == true) {
			panel.drive.setPrivateKey(privateKey);
			panel.drive.setPublicKey(publicKey);
		}
		
		panel.drive.setStartPath(newStartPath);

		panel.settingsFrame.dispose();
		
		View.update.updateListeners(UpdateOptions.DRIVE_UPDATE);
	}
	
	public boolean isValidPath(String newStartPath) {
		File folder = new File(panel.drive, newStartPath, "folder");
		
		try {
			Engine.listFolder(folder);
		} catch (Exception e) {
			String message = "Não foi possível listar essa pasta como pasta inicial.";
			message = new String(message.getBytes(), StandardCharsets.UTF_8);
			JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean isValidKeys(PublicKey publicKey, PrivateKey privateKey) {
		try {
			if(isKeysPairs(publicKey, privateKey) == false) {
				String message = "Chave pública e chave privada não são pares uma da outra.";
				message = new String(message.getBytes(), StandardCharsets.UTF_8);
				JOptionPane.showMessageDialog(panel, message);
				
				return false;
			}
		} catch (Exception e) {
			String message = "Erro ao tentar verificar se as chaves são pares uma da outra.";
			message = new String(message.getBytes(), StandardCharsets.UTF_8);
			JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

    public boolean isKeysPairs(PublicKey publicKey, PrivateKey privateKey) throws Exception {    	
        byte[] message = new byte[2048];
        (new SecureRandom()).nextBytes(message);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] cipherMessage = signature.sign();

        signature.initVerify(publicKey);
        signature.update(message);

        if(signature.verify(cipherMessage))
            return true;

        return false;
    }
}
