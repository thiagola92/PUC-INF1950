package example;

import java.security.*;

// Gera o message digest do argumento passado na linha de comando
public class MessageDigestExample {

	public static void main(String[] args) throws Exception {
		
		// Verifica se recebeu a quantia de argumento certos
		if(args.length != 1) {
			System.err.println("Usage: java MessageDigestExample text");
			System.exit(1);
		}
		
		// Pega os bytes da String passada
		byte[]plainText = args[0].getBytes("UTF8");
		System.out.print(args[0] + " >> ");
		for(int i = 0; i != plainText.length; i++)
			System.out.print(String.format("%02X ", plainText[i]));
		
		// Cria o MessageDigest com o algoritmo MD5
		MessageDigest messageDigest = MessageDigest.getInstance("MD5"); // algoritmo: MD5, provider: já que não falou nenhum, vai ser o primeiro da lista
		System.out.println("\n\n" + messageDigest.getProvider().getInfo() + "\n");
		
		// Calcula o digest
		messageDigest.update(plainText); // Se o arquivo for grande você vai precisar chamar varios updates para cada parte dele
		byte[] digest = messageDigest.digest();
		System.out.print("Digest (" + digest.length + "bytes/" + digest.length*8 + "bits): ");
		for(int i = 0; i != digest.length; i++)
			System.out.print(String.format("%02x", digest[i]));

	}

}
