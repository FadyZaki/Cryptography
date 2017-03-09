package deciphering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class MainClass {

	private static final int nSquared = 998001;
	private static final int n = Double.valueOf(Math.sqrt(nSquared)).intValue();
	private static final int mostOccuringDigraphCiphered = 471311;
	private static final int asciiT = 84;
	private static final int asciiH = 104;

	public static void main(String[] args) {

		ArrayList<Integer> possibleAs = new ArrayList<Integer>();
		for (int i = 1; i < nSquared; i++) {
			if (CoPrimeChecker.relativelyPrime(i, nSquared))
				possibleAs.add(i);
		}

		outerloop: for (Integer a : possibleAs) {
			BigInteger b = (BigInteger.valueOf(mostOccuringDigraphCiphered).subtract(BigInteger.valueOf(a).multiply(BigInteger.valueOf(asciiT * n + asciiH)))).mod(BigInteger.valueOf(nSquared));
			BigInteger aInverse = (BigInteger.valueOf(a).modInverse(BigInteger.valueOf(nSquared))
					.add(BigInteger.valueOf(nSquared))).mod(BigInteger.valueOf(nSquared));

			BufferedReader br = null;

			try {

				String sCurrentLine;
				br = new BufferedReader(new FileReader("16cipher.txt"));

				while ((sCurrentLine = br.readLine()) != null) {
					String[] words = sCurrentLine.split(":");
					if (words.length > 1) {
						for (int i = 0; i < words.length; i++) {
							int currentDigraph = Integer.valueOf(words[i]);
							BigInteger p = (aInverse.multiply(BigInteger.valueOf(currentDigraph).subtract(b)));
							p = p.mod(BigInteger.valueOf(nSquared));

							BigInteger x = p.divide(BigInteger.valueOf(n));
//							if (currentDigraph == 39518)
//								System.out.println(x.intValue());
							BigInteger y = p.mod(BigInteger.valueOf(n));
							if (x.intValue() < 32 || x.intValue() > 126 || y.intValue() < 32 || y.intValue() > 126)
								continue outerloop;

						}
					}

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

}
