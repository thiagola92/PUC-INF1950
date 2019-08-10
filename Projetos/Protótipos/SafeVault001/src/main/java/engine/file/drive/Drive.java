package engine.file.drive;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import plugin.Plugin;
import plugin.PluginList;

public class Drive {

	private String name;
	private String pluginName;
	private String startPath;
	private String secretPhrase;
	
	private Plugin plugin;
	private X509Certificate certificate;
	private PrivateKey privateKey;
	
	public Drive(String driveName, String pluginName) throws Exception {
		this.name = driveName;
		this.pluginName = pluginName;
		this.startPath = "";
		this.secretPhrase = "";
		
		this.plugin = PluginList.createPlugin(pluginName);
		this.certificate = null;
		this.privateKey = null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPluginName() {
		return pluginName;
	}
	
	public String getStartPath() {
		return startPath;
	}
	
	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}
	
	public String getSecretPhrase() {
		return secretPhrase;
	}
	
	public void setSecretPhrase(String secretPhrase) {
		this.secretPhrase = secretPhrase;
	}
	
	public Plugin getPlugin() {
		return plugin;
	}
	
	public X509Certificate getCertificate() {
		return certificate;
	}
	
	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}
	
	public PublicKey getPublicKey() {
		if(certificate == null)
			return null;
		
		return certificate.getPublicKey();
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	public String toString() {
		return name;
	}
	
}
