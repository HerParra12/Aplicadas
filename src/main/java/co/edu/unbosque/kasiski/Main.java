package co.edu.unbosque.kasiski;
import java.util.*;
import java.util.stream.*;

public class Main {

	private static String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static int counter = 0;	
	private static int maximoComun = 0;
	
	public Main() {}
	
	
	public static String group(String message) {
		Map <String, List <Integer>> mappingList = new HashMap<>();
		String newString = message.toUpperCase().replaceAll(" ", "");
		newString.lines()
		         .flatMap(string -> Stream.of(string.split("")))
		         .filter(character -> character.matches("[A-Z]"))
		         .collect(Collectors.groupingBy(letter -> letter, Collectors.counting()))
		         .entrySet()
		         .stream()
		         .filter(entry -> entry.getKey().length() >= 3 && entry.getValue() > 1)
		         .forEach(entry -> {
		        	if(!mappingList.containsKey(entry.getKey())) {
		        		StringBuilder builder = new StringBuilder(newString);
		        		List <Integer> listElements = new ArrayList <> ();
		        		int sizeMore = entry.getKey().length();
		        		int indexOf = 0;
		        		while(indexOf != -1) {
		        			indexOf = builder.indexOf(entry.getKey());
		        			if(indexOf != -1){
		        				builder.delete(indexOf, indexOf + entry.getKey().length());
		        				if(listElements.isEmpty()) {
		        					listElements.add(indexOf);
		        				}else {
		        					listElements.add(indexOf + sizeMore);
		        					sizeMore += entry.getKey().length();
		        				}
		        			}
		        		}
		        		mappingList.put(entry.getKey(), listElements);
		        	}
		         });
		    
			
		List <Integer> listElements = new ArrayList <> ();
		mappingList.forEach((key, list) -> {
			List <Integer> spaceWords = new ArrayList <> ();
			for(int i = list.size() -1; i > 0; i--) 
				spaceWords.add(list.get(i) - list.get(i -1));
			listElements.addAll(spaceWords);
		});
		
		
		List <List <Integer>> listOfList = new ArrayList <> (); 
		listElements.forEach(number -> {
			List <Integer> list = calculoDivisores(number);
			Collections.reverse(list);
			listOfList.add(list);
		});
		
		listOfList.forEach(list -> {
			list.forEach(number -> {
				listOfList.forEach(subList -> {
					if(subList.contains(number)) 
						counter ++;
				});
				if(counter == listOfList.size())
					maximoComun = Math.max(maximoComun, number);
				counter = 0;
			});
		});
		System.out.println("maximo comun divisor = "+ maximoComun);
		if(maximoComun == 1) return null;
		
		List <String> listString = new ArrayList <> ();
		int index = 0;
		while(index != maximoComun) {
			StringBuilder builder = new StringBuilder();
			int current_index = index;
			while(current_index < newString.length()) {                                 
				builder.append(newString.charAt(current_index));
				current_index += maximoComun;
			}
			listString.add(builder.toString());
			index ++;
		}
		listString.forEach(x -> {
			System.out.println("cadena = " + x);
			x.lines()
             .flatMap(string -> Stream.of(string.split("")))
             .collect(Collectors.groupingBy(key -> key.toString(), Collectors.counting()))
             .forEach((key, value) -> System.out.println("Key = " + key + ", value = " + value));
		});
		
		return "";
	}
	
	/*
	 * A = 8.34
	 * E = 12.60
	 * O = 7.70
	 * T = 9.37
	 * ETA
	 * ETAO -> A E O T
	 * ETAON -> A E O T N
	 */
	
	public static void main(String[] args) {
		Main.group("TSMVMMPPCWCZUGXHPECPRFAUEIOBQ"
				+ "WPPIMSFXIPCTSQPKSZNULOPACRDDP"
				+ "KTSLVFWELTKRGHIZSFNIDFARMUENO"
				+ "SKRGDIPHWSGVLEDMCMSMWKPIYOJST"
				+ "LVFAHPBJIRAQIWHLDGAIYOUX");
		System.out.println(Main.toStringMatrix(Main.matrix()));
	}	
	
	public static boolean isLetterAEOS(String letter) {
		return letter.matches("^[AEOS]$");
	}
	
	public static int sizeString(Map <String, Integer> map) {
		map.entrySet().stream().filter(mapa -> mapa.getKey().length() > 2 && mapa.getValue() > 1);
		return 0;
	}
	
	
	
	public static Map <String, Long> limitMapping(Map <String, Long> map){
		return map.entrySet()
				  .stream()
				  .sorted((x,y) -> y.getValue().compareTo(x.getValue()))
				  .limit(maximoComun)
				  .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
	}

	public static List <Integer> calculoDivisores(int numero){
		List <Integer> list = new ArrayList <> ();
		for(int i = 1; i < numero / 2; i++) {
			for(int j = i; j <= numero; j++) {
				if(i*j == numero && j != i) {
					list.add(i);
					list.add(j);
				}else if(i*j == numero && j == i) {
					list.add(i);
				}
			}
		}
		Collections.sort(list);
		return list;
	}
	
	public static char [][] matrix(){
		char matrix [] [] = new char [26][26];
		int index = 0;
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 26; j++) {
				matrix[i][j] = letters[index].toCharArray()[0];
				if(index < 25)
					index ++;
				else
					index = 0;
			}
			if(index < 25)
				index ++;
			else
				index = 0;
		}
		return matrix;
	}
	
	public static String toStringMatrix(char [][] matrix) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 26; j++) {
				builder.append("[ " + matrix[i][j] + " ]");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public static char [] llaveMatrix(String str, String key) {
		char llave [] = new char[str.length()];
		int index = 0;
		for(int i = 0; i < llave.length; i++) {
			llave[i] = key.charAt(index);
			if(index == key.length() -1)
				index = 0;
			else
				index ++;
		}
		return llave;
	}
	
	public static String cifrarMensage(String str, String key) {
		str = str.toUpperCase().replaceAll(" ", "");
		key = key.toUpperCase();
		StringBuilder builder = new StringBuilder();
		char llave [] = llaveMatrix(str, key);
		char matrix [][] = matrix();
		for(int i = 0; i < str.length(); i++)
			builder.append(matrix[indexCharDos(llave[i])][indexCharDos(str.charAt(i))]);
		System.out.println(builder);
		return builder.toString();
	}
	
	@SuppressWarnings("unused")
	public static String descifrarMensage(String str, String key) {
		StringBuilder builder = new StringBuilder();
		char llave [] = llaveMatrix(str, key);
		for(int i = 0; i < str.length(); i++) {
			int indexKey = indexCharDos(llave[i]);
			int indexLetter = indexCharDos(str.charAt(i));
		}
		return builder.toString();
	}
	
	
	public static int indexChar(String element) {
		for(int i = 0; i < letters.length; i++) {
			if(element.equals(letters[i]))
				return i;
		}
		return -1;
	}
	
	public static int indexCharDos(char element) {
		for(int i = 0; i < letters.length; i++) {
			if(letters[i].toCharArray()[0] == element)
				return i;
		}
		return -1;
	}
	
	public boolean isValidText(String str) {
		return str.matches("^([A-Z]?[a-z]{2,}[ ]?)+$");
	}
}