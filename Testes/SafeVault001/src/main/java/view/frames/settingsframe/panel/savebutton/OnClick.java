package view.frames.settingsframe.panel.savebutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.X509Certificate;

import javax.swing.JOptionPane;

import engine.Engine;
import engine.file.File;
import view.View;
import view.frames.settingsframe.panel.Panel;
import view.stringformat.StringFormat;
import view.update.UpdateOptions;

public class OnClick implements ActionListener {
	
	private Panel panel;
	
	public OnClick(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newStartPath = panel.startPath.getText();
		
		if(isValidPath(newStartPath) == false)
			return;
		
		X509Certificate certificate = panel.certificate;
		PrivateKey privateKey = panel.privateKey;
		
		if(certificate != null && privateKey != null) {
			if(isValidKeys(certificate, privateKey) == false)
				return;
		}
		
		String secretPhrase = new String(panel.secretPhrase.getPassword());
		String confirmSecretPhrase = new String(panel.confirmSecretPhrase.getPassword());
		
		if(secretPhrase.isEmpty() && confirmSecretPhrase.isEmpty()) {
			secretPhrase = panel.drive.getSecretPhrase();
			confirmSecretPhrase = panel.drive.getSecretPhrase();
		}
		
		if(isEqual(secretPhrase, confirmSecretPhrase) == false)
			return;

		panel.drive.setName(panel.name.getText());
		panel.drive.setStartPath(newStartPath);
		panel.drive.setPrivateKey(privateKey);
		panel.drive.setCertificate(certificate);
		panel.drive.setSecretPhrase(secretPhrase);

		panel.settingsFrame.dispose();
		
		View.update.updateListeners(UpdateOptions.DRIVE_UPDATED);
	}
	
	public boolean isValidPath(String newStartPath) {
		File folder = new File(panel.drive, newStartPath, "folder");
		
		try {
			Engine.listFolder(folder);
		} catch (Exception e) {
			StringFormat message = new StringFormat("Não foi possível listar essa pasta como pasta inicial.");
			JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	public boolean isValidKeys(X509Certificate certificate, PrivateKey privateKey) {
		try {
			if(isKeysPairs(certificate, privateKey) == false) {
				StringFormat message = new StringFormat("Chave pública e chave privada não são pares uma da outra.");
				JOptionPane.showMessageDialog(panel, message);
				
				return false;
			}
		} catch (Exception e) {
			StringFormat message = new StringFormat("Erro ao tentar verificar se as chaves são pares uma da outra.");
			JOptionPane.showMessageDialog(panel, message);
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

    public boolean isKeysPairs(X509Certificate certificate, PrivateKey privateKey) throws Exception {    	
        byte[] message = new byte[2048];
        (new SecureRandom()).nextBytes(message);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] cipherMessage = signature.sign();

        signature.initVerify(certificate);
        signature.update(message);

        if(signature.verify(cipherMessage))
            return true;

        return false;
    }
    
    public boolean isEqual(String phrase1, String phrase2) {
    	if(phrase1.equals(phrase2))
    		return true;
    	
		StringFormat message = new StringFormat("As frases secretas não são iguais.");
		JOptionPane.showMessageDialog(panel, message);
    	
    	return false;
    }
}
