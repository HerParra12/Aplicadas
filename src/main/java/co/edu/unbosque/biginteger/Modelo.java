package co.edu.unbosque.biginteger;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Modelo {

	private BigInteger counter = BigInteger.ONE;
	
	public Modelo() {}
	
	public String factorial(int number) {
		BigInteger big = BigInteger.ONE;
		for(int i = 1; i <= number; i++) 
			big = big.multiply(new BigInteger(String.valueOf(i)));
		return big.toString();
	}
	
	public String counter(List <BigInteger> list) {
		list.parallelStream()
		    .collect(Collectors.groupingBy(number -> number, Collectors.counting()))
		    .entrySet()
		    .stream()
		    .map(entry -> entry.getValue() +1)
		    .toList()
		    .forEach(element -> counter = counter.multiply(BigInteger.valueOf(element)));
		return counter.toString();
	}
	
		
	public List <BigInteger> divisoresPrimos(String number) {
		BigInteger numero = new BigInteger(number);
		List <BigInteger> listElements =new ArrayList <> ();
		for (BigInteger index = BigInteger.TWO; index.compareTo(numero) < 1; index = index.add(BigInteger.ONE)){
			while(numero.mod(index).equals(BigInteger.ZERO)){
				numero = numero.divide(index);
				listElements.add(index);
			}
		}
		return listElements;
	}	
	
	
	public String complement(int number) {
		counter = BigInteger.ONE;
		String result = counter(divisoresPrimos(factorial(number)));
		System.out.println(result);
		return result;
	}
	
	public boolean isValidNumber(String number) {
		return number.matches("^([0-9]|[1-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3}|([1-4][0-9]{4}|[5][0]{4}))$");
	}
}