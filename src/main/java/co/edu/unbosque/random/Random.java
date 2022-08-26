package co.edu.unbosque.random;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Random {

	
	/* I = NUMERO
	 * 
	 * 
	 */
	public String factorial(String numero) {
		BigInteger result = new BigInteger("1");
		for(int i = 1; i <= Integer.parseInt(numero); i++) 
			result = result.multiply(new BigInteger(String.valueOf(i)));
		return result.toString();
	}
	
	public int divisores(String numero) {
		BigInteger number = new BigInteger(numero);
		List <String> list = new ArrayList<>();
		for(var index = new BigInteger("1"); index.compareTo(number) < 1; index = index.add(new BigInteger("1"))) 
			if(number.mod(index).compareTo(new BigInteger("0")) == 0) 
				list.add(index.toString());
		System.out.println("List = " + list);
		return list.size();
	}
	
	public int div(String number) {
		List <String> divisores = new ArrayList<>();
		listNumbers(number).parallelStream().forEach(element -> {
			if(new BigInteger(number).mod(new BigInteger(element)).compareTo(new BigInteger("0")) == 0) {
				divisores.add(element);
			}
		});
		return divisores.size();
	}
	
	public List <String> listNumbers(String number){
		List <String> listElements = new ArrayList <> ();
		for(var index = new BigInteger("1"); index.compareTo(new BigInteger(number)) < 1; index = index.add(new BigInteger("1")))
			listElements.add(index.toString());
		return listElements;
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		String result = random.factorial("50000");
		System.out.println("result = " + result + "\nlength = " + result.length() + "\nlist = ");
		System.out.println("divisores = " + random.div(result));
		
		//System.out.println(random.divisores(result));
	}
	
	
	
}
