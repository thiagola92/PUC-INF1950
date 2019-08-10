package view.frames.settingsframe.panel.importcertificate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import view.stringformat.StringFormat;

public class OnClick implements ActionListener {
	
	public ImportCertificateButton importCertificateButton;
	
	public OnClick(ImportCertificateButton importCertificateButton) {
		this.importCertificateButton = importCertificateButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(fileChooser.showOpenDialog(importCertificateButton.panel) != JFileChooser.APPROVE_OPTION)
			return;

		try {
			String certificatePath = fileChooser.getSelectedFile().getPath();
			byte[] certificateBytes = Files.readAllBytes(Paths.get(certificatePath));
			X509Certificate certificate = getCertificate(certificateBytes);
			
			importCertificateButton.panel.certificate = certificate;
			importCertificateButton.panel.publicKeyTextField.setKey(certificate.getPublicKey());
		} catch(Exception e) {
			StringFormat message = new StringFormat("Certificado inválido.");
			JOptionPane.showMessageDialog(importCertificateButton.panel, message);
		}
	}
	
	public static X509Certificate getCertificate(byte[] certificateBytes) throws Exception {
        InputStream certificateInputStream = new ByteArrayInputStream(certificateBytes);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        return (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);
    }

}
