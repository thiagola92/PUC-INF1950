package example;

import java.security.*;
import javax.crypto.*;

public class AsymmetricKeyCipherExample {

	public static void main(String[] args) throws Exception {
		
		// Verifica se recebeu a quantia de argumento certos
		if(args.length != 1) {
			System.err.println("Usage: MessageAuthenticationCodeExample text");
			System.exit(1);
		}
		
		// Pega os bytes da String passada
		byte[]plainText = args[0].getBytes("UTF8");
		System.out.print(args[0] + " >> ");
		for(int i = 0; i != plainText.length; i++)
			System.out.print(String.format("%02X ", plainText[i]));
		
		// Gera um par de chaves RSA
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		
		// Exibindo a chave privada
		byte[] privateKey = key.getPrivate().getEncoded();
		System.out.print("\n\nChave privada: ");
		for(int i = 0; i != privateKey.length; i++)
			System.out.print(String.format("%02x", privateKey[i]));
		
		// Exibindo a chave pública
		byte[] publicKey = key.getPublic().getEncoded();
		System.out.print("\n\nChave publica: ");
		for(int i = 0; i != publicKey.length; i++)
			System.out.print(String.format("%02x", publicKey[i]));
		
		// Define uma cifra com algoritmo RSA, modo de operação ECB e preenchimento PKCS1
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		System.out.println("\n\n" + cipher.getProvider().getInfo());
		
		// Criptografa o texto plano utilizando a chave publica
		cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
		byte[] cipherText = cipher.doFinal(plainText);
		
		// Exibe o texto cifrado
		System.out.print("\nTexto cifrado: ");
		for(int i = 0; i != cipherText.length; i++)
			System.out.print(String.format("%02x", cipherText[i]));
		
		// Descriptografa o texto plano utilizando a chave privada
		cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
		byte[] newTextPlain = cipher.doFinal(cipherText);
		
		// Exibe o texto plano
		System.out.print("\n\nTexto decriptado: ");
		for(int i = 0; i != newTextPlain.length; i++)
			System.out.print(String.format("%c", newTextPlain[i]));
		

	}

}
