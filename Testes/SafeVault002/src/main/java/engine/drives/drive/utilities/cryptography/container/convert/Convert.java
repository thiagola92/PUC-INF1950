package engine.drives.drive.utilities.cryptography.container.convert;

public class Convert {
	
	private int size = 4;
	
	public Convert() {
		
	}
	
	public int size() {
		return size;
	}
	
	public byte[] to_bytes(int number) {
		byte[] bytes = new byte[size];
		
		for(int i = size - 1; i >= 0; i--) {
			bytes[i] = (byte)(number & 0xFF);
			number = number >> 8;
		}
		
		return bytes;
	}
	
	public int to_number(byte[] bytes) {
		int number = 0;
		
		for(int i = 0; i < size; i++) {
			number = number << 8;
			number = number | (bytes[i] & 0xFF);
		}
		
		return number;
	}

}
