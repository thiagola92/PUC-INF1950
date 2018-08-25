package example;

import java.security.*;

public class DigitalSignatureExample {

	public static void main(String[] args) throws Exception {
		
		// Verifica se recebeu a quantidade de argumentos certa
		if(args.length != 1) {
			System.err.println("Usage: MessageAuthenticationCodeExample text");
			System.exit(1);
		}
		
		// Pega os bytes da String passada
		byte[] plainText = args[0].getBytes("UTF8");
		System.out.print(args[0] + " >> ");
		for(int i = 0; i != plainText.length; i++)
			System.out.print(String.format("%02X ", plainText[i]));
		
		// Gera o par de chaves RSA
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();

		// Define um objeto signature para utilizar MD5 e RSA
		Signature sig = Signature.getInstance("MD5WithRSA");
		System.out.println("\n\n" + sig.getProvider().getInfo());
		
		// Iniciar a assinatura utilizando a chave privada
		sig.initSign(key.getPrivate());
		
		// Escolhe o texto plano e cria a assinatura
		sig.update(plainText);
		byte[] signature = sig.sign();
		
		// Exibe a assinatura
		System.out.print("\nAssinatura: ");
		for(int i = 0; i != signature.length; i++)
			System.out.print(String.format("%02x", signature[i]));
		
		// Inicia a verificação utilizando a chave pública
		sig.initVerify(key.getPublic());
		
		// Escolhe o texto plano a ser verificado e verifica
		sig.update(plainText);
		if(sig.verify(signature)) 
			System.out.println("\n\nAssinatura válida");
		else
			System.err.println("\n\nAssinatura inválida");
	}

}
