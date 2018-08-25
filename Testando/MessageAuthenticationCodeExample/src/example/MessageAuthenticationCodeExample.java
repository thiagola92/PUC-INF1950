package example;

import javax.crypto.*;

public class MessageAuthenticationCodeExample {

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
		
		// Gera uma chave com o algoritmo HmacMD5
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey MD5key = keyGen.generateKey();
		
		// Exibindo a chave
		byte[] keyBytes = MD5key.getEncoded();
		System.out.print("\n\nChave secreta: ");
		for(int i = 0; i != keyBytes.length; i++)
			System.out.print(String.format("%02x", keyBytes[i]));
		
		// Define um objeto MAC
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(MD5key); // Algoritmo precisa da chave primeiro para poder fazer os calculos
		mac.update(plainText);
		System.out.println("\n\n" + mac.getProvider().getInfo());
		
		// Exibindo a Message Authentication Code
		byte[] macFinal = mac.doFinal();
		System.out.print("\nMAC (" + macFinal.length + "bytes/" + macFinal.length*8 + "bits): ");
		for(int i = 0; i != macFinal.length; i++)
			System.out.print(String.format("%02x", macFinal[i]));
	}

}
