package deciphering;

public class CoPrimeChecker {

	private static int gcd(int a, int b) {
	    int t;
	    while(b != 0){
	        t = a;
	        a = b;
	        b = t%b;
	    }
	    return a;
	}
	
	public static boolean relativelyPrime(int a, int b) {
	    return gcd(a,b) == 1;
	}
	
}
