import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class DoubleSHA256 {

	final protected static char[] hexArray = "0123456789abcdef".toCharArray();

	public static byte[] performSHATwice(byte[] input) {

		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return digester.digest(digester.digest(input));
	}

	public static byte[] performSHAOnce(byte[] input) {

		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return digester.digest(input);
	}

	public static byte[] performSHAOnceOnString(String input) {

		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return digester.digest(input.getBytes());
	}

	private static String bytesToHex(final byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];

		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}

		return new String(hexChars);
	}

	public static void main(String[] args) throws DecoderException {
		String c = "c";
		String d = "d";
		String hash1 = convertToLittleEndian("fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d5903b85055620603");
		String hash2 = convertToLittleEndian("568a301ab7df10a2aa916d2edc73ff7660409b8223d72b8e6b3259ea551b3326");
		String hash3 = convertToLittleEndian("c8dcc7b350de1612febd951b96596648df0ddbd0a1c00fdd92f7b8b32c99b812");
		String hash4 = convertToLittleEndian("c79530adb7ff69abc48a7dba694cb461e047715cbd8addc86c22edd1b2664079");

		String cHashed = convertToLittleEndian(bytesToHex(performSHAOnceOnString(c)));
		String dHashed = convertToLittleEndian(bytesToHex(performSHAOnceOnString(d)));

		String cdHashed = convertToLittleEndian(bytesToHex(performSHAOnce(Hex.decodeHex((cHashed + dHashed).toCharArray()))));
		String tempHash = convertToLittleEndian(bytesToHex(performSHAOnce(Hex.decodeHex((hash1 + cdHashed).toCharArray()))));
		tempHash = convertToLittleEndian(bytesToHex(performSHAOnce(Hex.decodeHex((tempHash + hash2).toCharArray()))));
		tempHash = convertToLittleEndian(bytesToHex(performSHAOnce(Hex.decodeHex((tempHash + hash3).toCharArray()))));
		tempHash = convertToLittleEndian(bytesToHex(performSHAOnce(Hex.decodeHex((tempHash + hash4).toCharArray()))));

		String merkleRootLittleEndian = tempHash;

		String versionNumber = "02000000";
		String previousHash = "0000000000000000AAAAAAAAAAAAAAAA";
		String target = "18AAAAAA";
		String time = "4d64fe57";
		String nonce = "66ae0d00";

		String header_hex = versionNumber + previousHash + merkleRootLittleEndian + time + target + nonce;

		System.out.println(bytesToHex(performSHATwice(Hex.decodeHex(header_hex.toCharArray()))));
	}

	private static String convertToLittleEndian(String be) {
		String le = "";
		for (int i = be.length() - 1; i > 0; i -= 2) {
			le += be.charAt(i - 1);
			le += be.charAt(i);
		}
		return le;
	}
}