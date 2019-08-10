package view.settingsframe.panel.importpublickey;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import _main.Main;
import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class ImportPublicKey extends JButton {
	
	private Panel panel;
	
	public ImportPublicKey(Panel panel) {
		this.panel = panel;
		
		this.setText("Import from Certificate");
		
		this.addActionListener((event) -> click());
	}
	
	private String file() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(chooser.showOpenDialog(panel) != JFileChooser.APPROVE_OPTION)
			return null;
		
		return chooser.getSelectedFile().getPath();
	}
	
	private X509Certificate certificate(byte[] bytes) throws Exception {
        InputStream certificateInputStream = new ByteArrayInputStream(bytes);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        return (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);
	}
	
	private PublicKey import_publickey(String file) throws Exception {
		Path path = Paths.get(file);
		byte[] bytes = Files.readAllBytes(path);
		X509Certificate certificate = certificate(bytes);
		
		return certificate.getPublicKey();
	}
	
	private PublicKey publickey(String file) {
		try {
			return import_publickey(file);
		} catch(Exception e) {
			String message = Main.view.utilities().strings().utf8("Falha na importação da chave pública.");
	        JOptionPane.showMessageDialog(panel, message);
	        
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void click() {
		String file = file();
		
		if(file == null)
			return;
		
		PublicKey publickey = publickey(file);
		
		if(publickey == null)
			return;
		
		panel.publickey_field().set_key(publickey);
	}

}
