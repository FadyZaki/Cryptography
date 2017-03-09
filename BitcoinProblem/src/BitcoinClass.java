import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.EndianUtils;

public class BitcoinClass {

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		// // The input string for this test
		// final String hexString = "0x1a44b9f2";
		// // Check length, in characters
		// System.out.println(Integer.decode(hexString).BYTES);

		// String c = "c";
		// String d = "d";
		// String hash1 =
		// "fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d5903b85055620603";
		// String hash2 =
		// "568a301ab7df10a2aa916d2edc73ff7660409b8223d72b8e6b3259ea551b3326";
		// String hash3 =
		// "c8dcc7b350de1612febd951b96596648df0ddbd0a1c00fdd92f7b8b32c99b812";
		// String hash4 =
		// "c79530adb7ff69abc48a7dba694cb461e047715cbd8addc86c22edd1b2664079";
		//
		// String cdHashed = getSHA2HexValue(getSHA2HexValue(c) +
		// getSHA2HexValue(d));
		// String tempHash = getSHA2HexValue(hash1 + cdHashed);
		// tempHash = getSHA2HexValue(tempHash + hash2);
		// tempHash = getSHA2HexValue(tempHash + hash3);
		// tempHash = getSHA2HexValue(tempHash + hash4);
		//
		// String merkleRootBigEndian = tempHash;
		// String merkleRootLittleEndian = "";
		// for (int i = merkleRootBigEndian.length() - 1; i > 0; i -= 2) {
		// merkleRootLittleEndian += merkleRootBigEndian.charAt(i - 1);
		// merkleRootLittleEndian += merkleRootBigEndian.charAt(i);
		// }
		// System.out.println(merkleRootBigEndian);
		// System.out.println(merkleRootLittleEndian);
		//
		// String versionNumber = "02000000";
		// String previousHash = "0000000000000000AAAAAAAAAAAAAAAA";
		// String target = "18AAAAAA";
		// String time = "4d64fe57";
		// String nonce = "66ae0d00";

		String versionNumber = "01000000";
		String previousHash = "81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000";
		String merkleRootLittleEndian = "e320b6c2fffc8d750423db8b1eb942ae710e951ed797f7affc8892b0f1fc122b";
		String time = "c7f5d74d";
		String target = "f2b9441a";
		String nonce = "42a14695";

		String header_hex = versionNumber + previousHash + merkleRootLittleEndian + time + target + nonce;
		String header_bin = hexToBin(header_hex);
		System.out.println(header_hex);
		System.out.println(header_bin);
		byte[] header_bin_hashed = getSHA2HexValue(header_bin);
		System.out.println(Hex.encodeHexString(header_bin_hashed));

	}

	/**
	 * // * Return hex string // * @param str // * @return //
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	// public static String getSHA2HexValue(String str) {
	// byte[] cipher_byte;
	// try {
	// MessageDigest md = MessageDigest.getInstance("SHA-256");
	// md.update(str.getBytes());
	// cipher_byte = md.digest();
	// StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
	// for (byte b : cipher_byte) {
	// sb.append(String.format("%02x", b & 0xff));
	// }
	// return sb.toString();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return "";
	// }

	public static byte[] getSHA2HexValue(String text) {
		return DigestUtils.sha256((DigestUtils.sha256(text)));
	}

	static String hexToBin(String s) {
		String hex = new BigInteger("f" + s, 16).toString(2);
		return hex.substring(4, hex.length());
	}

}
