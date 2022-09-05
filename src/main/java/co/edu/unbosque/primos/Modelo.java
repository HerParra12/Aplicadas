package co.edu.unbosque.primos;
import java.math.BigInteger;

public class Modelo {

	public String isPrime(String num) {
		BigInteger number = new BigInteger(num);
		BigInteger primes [] = {BigInteger.TWO, new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11"), 
				                new BigInteger("13"), new BigInteger("17"), new BigInteger("19"), new BigInteger("23")};
		boolean isPrime = true;
		for(int i = 0; i < primes.length && isPrime; i++) 
			if(number.mod(primes[i]).equals(BigInteger.ZERO) && !number.equals(primes[i])) 
				isPrime = false;
		return isPrime? "SI" : "NO";
	}
	
	public boolean isValidNumber(String number) {
		return number.matches("[0-9]+");
	}
	
}
