package deciphering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class TrialClass {

	private static final int nSquared = 998001;
	private static final int n = Double.valueOf(Math.sqrt(nSquared)).intValue();
	private static final int a = 10007;
	private static final int b = 13;
	
	public static void main(String[] args) {

		BufferedReader br = null;

		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader("16cipher.txt"));
			
			BigInteger aInverse = (BigInteger.valueOf(a).modInverse(BigInteger.valueOf(nSquared))
					.add(BigInteger.valueOf(nSquared))).mod(BigInteger.valueOf(nSquared));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] words = sCurrentLine.split(":");
				if (words.length > 1) {
					for (int i = 0; i < words.length; i++) {
						int currentDigraph = Integer.valueOf(words[i]);
						BigInteger p = (aInverse.multiply(BigInteger.valueOf(currentDigraph).subtract(BigInteger.valueOf(b))));
						p = p.mod(BigInteger.valueOf(nSquared));

						BigInteger x = p.divide(BigInteger.valueOf(n));
						BigInteger y = p.mod(BigInteger.valueOf(n));
						
						System.out.print(Character.toString ((char) x.intValue()));
						System.out.print(Character.toString ((char) y.intValue()));
						

					}
				}
				
				System.out.println("");

			}
			System.out.println("a is equal to " + a);
			System.out.println("b is equal to " + b);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
