package co.edu.unbosque.kasiski;
import java.util.*;
import java.util.stream.*;

public class Main {

	private static String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static int counter = 0;	
	private static int maximoComun = 0;
	
	public Main() {}
	
	
	public static String group(String message) {
		String newString = message.toUpperCase().replaceAll(" ", "").replaceAll(":", "");
		Map <String, Integer> map = new TreeMap <>();
		Map <String, List <Integer>> mappingList = new HashMap<>();
		for(int i = 0; i < newString.length(); i++) {
			StringBuilder builder = new StringBuilder();
			for(int j = i; j < i+12 && j < newString.length(); j++) {
				builder.append(newString.charAt(j));
				map.put(builder.toString(), map.getOrDefault(builder.toString(), 0) +1);
			}
		}
		map.entrySet()
		   .stream()
		   .filter(entry -> entry.getKey().length() >= 3 && entry.getValue() > 1)
		   .forEach(entry -> {
			   if(!mappingList.containsKey(entry.getKey())) {
				   StringBuilder builder = new StringBuilder(newString);
				   List <Integer> list = new ArrayList <> ();
				   int sizeMore = entry.getKey().length();
				   int indexOf = 0;
					while(indexOf != -1) {
						indexOf = builder.indexOf(entry.getKey());
						if(indexOf != -1) {
							builder.delete(indexOf, indexOf + entry.getKey().length());
							if(list.isEmpty()) {
								list.add(indexOf);
							}else {
								list.add(indexOf + sizeMore);
								sizeMore = sizeMore + entry.getKey().length();
							}
						}
					}
					mappingList.put(entry.getKey(), list);
			   }
		   });
		List <Integer> listElements = new ArrayList <> ();
		mappingList.forEach((key, list) -> {
			List <Integer> spaceWords = new ArrayList <> ();
			for(int i = list.size() -1; i > 0; i--) 
				spaceWords.add(list.get(i) - list.get(i -1));
			listElements.addAll(spaceWords);
		});
		
		List <List <Integer>> listOfList = listElements.stream()
		            								   .map(number -> calculoDivisores(number))
		            								   .toList();
		
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
		
		Map <String, Map <String, Long>> mappingOcurrences = new HashMap <> ();
		listString.forEach(string -> {
			Map <String, Long> mappingLetters = string.lines()
													  .flatMap(x -> Stream.of(x.split("")))
													  .collect(Collectors.groupingBy(key -> key.toString(), HashMap :: new, Collectors.counting()));
			for(String letter : letters) 
				if(!mappingLetters.containsKey(letter))
					mappingLetters.put(letter, 0L);
			mappingOcurrences.put(string, mappingLetters);
		});
		
		List <String> listFinal = new ArrayList <> ();
		Map <String, String> mapa = new HashMap <> ();
		mappingOcurrences.forEach((string, mapOcurrences) -> {
			Map <String, Long> ocurrences = new HashMap <> ();
			mapOcurrences.forEach((key, value) -> {
				int position = indexChar(key);
				int indexA = position +4 < 25? position +4 : (position +4) % 26;
				int indexB = position +14 < 25? position +14 : (position +14) % 26;
				long suma = mapOcurrences.get(letters[indexA]) + mapOcurrences.get(letters[indexB]);
				ocurrences.put(key, suma);
			});
			String letraLlave = ocurrences.entrySet().stream().max((x,y) -> x.getValue().compareTo(y.getValue())).get().getKey();
			listFinal.add(letraLlave);
			mapa.put(string, letraLlave);
		});
		StringBuilder llave = new StringBuilder();
		listString.forEach(cadena -> {
			llave.append(mapa.get(cadena));
		});
		return llave.toString();
	}
	
	
	public static void main(String[] args) {		
		Main.group("TSMVMMPPCWCZUGXHPECPRFAUEIOBQ"
				+ "WPPIMSFXIPCTSQPKSZNULOPACRDDP"
				+ "KTSLVFWELTKRGHIZSFNIDFARMUENO"
				+ "SKRGDIPHWSGVLEDMCMSMWKPIYOJST"
				+ "LVFAHPBJIRAQIWHLDGAIYOUX");
	}	
	
	public static List <Integer> calculoDivisores(int numero){
		List <Integer> list = new ArrayList <> ();
		for(int i = 2; i <= numero; i++) {
			while(numero % i == 0) {
				numero = numero / i;
				list.add(i);
			}
		}
		Collections.sort(list);
		return list;
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
}