package example;

import java.security.*;
import javax.crypto.*;

public class SymmetricKeyCipherExample {

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
		
		// Gera uma chave para o DES
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		Key key = keyGen.generateKey();
		
		// Exibindo a chave
		byte[] keyBytes = key.getEncoded();
		System.out.print("\n\nChave: ");
		for(int i = 0; i != keyBytes.length; i++)
			System.out.print(String.format("%02x", keyBytes[i]));

		// Define uma cifra com algoritmo DES, modo de operação ECB e preenchimento PKCS5
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		System.out.println("\n\n" + cipher.getProvider().getInfo());
		
		// Criptografando utilizando a chave e o text plano
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cypherText = cipher.doFinal(plainText);
		
		// Exibindo o cypherText
		System.out.print("\nMensagem cifrada (" + cypherText.length + "bytes/" + cypherText.length*8 + "bits): ");
		for(int i = 0; i != cypherText.length; i++)
			System.out.print(String.format("%02x", cypherText[i]));
		System.out.println();
		
		// Descriptografando utilizando a chave e o text plano
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] newPlainText = cipher.doFinal(cypherText);
		
		// Exibindo o texto pois descriptografar
		System.out.print("\nMensagem plana (" + newPlainText.length + "bytes/" + newPlainText.length*8 + "bits): ");
		System.out.print(new String(newPlainText, "UTF8"));
		
	}

}
