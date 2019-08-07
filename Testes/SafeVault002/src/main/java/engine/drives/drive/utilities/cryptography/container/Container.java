package engine.drives.drive.utilities.cryptography.container;

import java.util.Arrays;
import java.util.HashMap;

import engine.drives.drive.utilities.cryptography.container.convert.Convert;

public class Container {
	
	private Convert convert;
	
	public Container() {
		convert = new Convert();
	}
	
	private byte[] first_information(byte[] container) {
		int lengthend = convert.size() + 1;
		byte[] size = Arrays.copyOfRange(container, 1, lengthend);
		
		int valueend = lengthend + convert.to_number(size);
		return Arrays.copyOfRange(container, lengthend, valueend);
	}
	
	private void store_information(byte id, byte[] info, HashMap<String, byte[]> information) {
		if(id == 1)
			information.put("seed", info);
		else if(id == 2)
			information.put("signature", info);
	}
	
	private byte[] slice_first_information(byte[] container) {
		int lengthend = convert.size() + 1;
		byte[] size = Arrays.copyOfRange(container, 1, lengthend);
		
		int valueend = lengthend + convert.to_number(size);
		return Arrays.copyOfRange(container, valueend, container.length);
	}
	
	public HashMap<String, byte[]> open(byte[] container) {
		HashMap<String, byte[]> information = new HashMap<String, byte[]>();
		
		for(int i = 0; i < 2; i++) {
			byte[] info = first_information(container);
			store_information(container[0], info, information);
			container = slice_first_information(container);
		}
		
		information.put("content", container);
		
		return information;
	}
	
	public byte[] create(byte[] seed, byte[] signature, byte[] content) throws Exception {
		byte[] seedid = new byte[1]; 
		byte[] seedsize = convert.to_bytes(seed.length);
		seedid[0] = 1;
		
		byte[] signatureid = new byte[1];
		byte[] signaturesize = convert.to_bytes(signature.length);
		signatureid[0] = 2;
		
		int slice1 = 1;
		int slice2 = slice1 + seedsize.length;
		int slice3 = slice2 + seed.length;
		int slice4 = slice3 + 1;
		int slice5 = slice4 + signaturesize.length;
		int slice6 = slice5 + signature.length;
		int slice7 = slice6 + content.length;
		
		byte[] container = new byte[slice7];

		System.arraycopy(seedid, 0, container, 0, seedid.length);
		System.arraycopy(seedsize, 0, container, slice1, seedsize.length);
		System.arraycopy(seed, 0, container, slice2, seed.length);
		System.arraycopy(signatureid, 0, container, slice3, signatureid.length);
		System.arraycopy(signaturesize, 0, container, slice4, signaturesize.length);
		System.arraycopy(signature, 0, container, slice5, signature.length);
		System.arraycopy(content, 0, container, slice6, content.length);
		
		return container;
	}

}
