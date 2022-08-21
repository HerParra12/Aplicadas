package co.edu.unbosque.kasiski;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {

	private static String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static int counter = 0;	
	private static int maximoComun = 0;
	
	public Main() {}
	
	
	public static String group(String message) {
		String newString = message.toUpperCase().replaceAll(" ", "").replaceAll(":", "");
		System.out.println("Size = " + newString.length());
		Map <String, Integer> map = new TreeMap <>();
		Map <String, Integer> mapping = new TreeMap <>();
		Map <String, List <Integer>> mappingList = new HashMap<>();
		for(int i = 0; i < newString.length(); i++) {
			StringBuilder builder = new StringBuilder();
			for(int j = i; j < i+26 && j < newString.length(); j++) {
				builder.append(newString.charAt(j));
				map.put(builder.toString(), map.getOrDefault(builder.toString(), 0) +1);
			}
		}
		
		map.forEach((key, value) -> {
			if(key.length() >= 3 && value > 1)                 
				mapping.put(key, value);
		});
		
		mapping.forEach((key, value) -> {
			if(!mappingList.containsKey(key)) {
				System.out.println("Key = " + key + ", value = " + value);
				StringBuilder builder = new StringBuilder(newString);
				List <Integer> list = new ArrayList <> ();
				int sizeMore = key.length();
				int indexOf = 0;
				while(indexOf != -1) {
					indexOf = builder.indexOf(key);
					if(indexOf != -1) {
						builder.delete(indexOf, indexOf + key.length());
						if(list.isEmpty()) {
							list.add(indexOf);
						}else {
							list.add(indexOf + sizeMore);
							sizeMore = sizeMore + key.length();
						}
					}
				}
				mappingList.put(key, list);
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
		listElements.forEach(element -> {
			List <Integer> list = calculoDivisores(element);
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
		System.out.println("maximo = " + maximoComun);
		
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
			System.out.println("Sub = "+ string);
			
			Map <String, Long> mapFinal = limitMapping(mapOcurrences);
			Map <String, Long> ocurrences = new HashMap <> ();
			mapFinal.forEach((key, value) -> {
				int position = indexChar(key);
				int indexA = position +4 < 26? position +4 : (position +4) % 27;
				int indexB = position +15 < 26? position +15 : (position +15) % 27;
				int indexC = position +19 < 26? position +19 : (position +19) % 27;
				//System.out.println("Key = " + key + ",value = " + value + ", position = " + position + ", indexA = " + indexA + ", letra=" + letters[indexA] + ", indexB = " + indexB + ",letra=" +letters[indexB]);
				long suma = mapOcurrences.get(letters[indexA]) + mapOcurrences.get(letters[indexB]) + mapOcurrences.get(letters[indexC]);
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
		System.out.println("LLave del metodo = " + llave);
		return llave.toString();
	}
	
	
	public static void main(String[] args) {
		Main.group("PBVRQ VICAD SKAÑS DETSJ PSIED BGGMP SLRPW RÑPWY EDSDE ÑDRDP CRCPQ MNPWK"
				+ "UBZVS FNVRD MTIPW UEQVV CBOVN UEDIF QLONM VNUVR SEIKA ZYEAC EYEDS ETFPH"
				+ "LBHGU ÑESOM EHLBX VAEEP UÑELI SEUEF WHUNM CLPQP MBRRN BPVIÑ MTIBV VEÑIC"
				+ "ANSJA MTJOK MDODS ELPWI UFOZM QMVNF OHASE SRJWR SFQCO TWVMB JGRPW VSUEX"
				+ "INQRS JEUEM GGRBD GNNIL AGSJI DSVSU EEINT GRUEE TFGGM PORDF OGTSS TOSEQ"
				+ "OÑTGR RYVLP WJIFW XOTGG RPQRR JSKET XRNBL ZETGG NEMUO TXJAT ORVJH RSFHV" 
				+ "NUEJI BCHAS EHEUE UOTIE FFGYA TGGMP IKTBW UEÑEN IEEU");
		//System.out.println("\n\n----------\n\n");
		
		Main.group("TSMVMMPPCWCZUGXHPECPRFAUEIOBQ"
				+ "WPPIMSFXIPCTSQPKSZNULOPACRDDP"
				+ "KTSLVFWELTKRGHIZSFNIDFARMUENO"
				+ "SKRGDIPHWSGVLEDMCMSMWKPIYOJST"
				+ "LVFAHPBJIRAQIWHLDGAIYOUX");
		//Main.group("RGHIE OJUHS USASK XRAWR FOBIY CYVRS FGBBL VNFDE YZSZR RWPPW XVNRG HRJAK RBWVR SFIYQ MEYGW HRPWM AUJIF OJGBA GYAAA RVAGH RQAIA QSVNQ LIESK TNFSP BUJEE IFEZO QSESX MPOUM N");
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
		char matrix [] [] = new char [27][27];
		int index = 0;
		for(int i = 0; i < 27; i++) {
			for(int j = 0; j < 27; j++) {
				matrix[i][j] = letters[index].toCharArray()[0];
				if(index < 26)
					index ++;
				else
					index = 0;
			}
			if(index < 26)
				index ++;
			else
				index = 0;
		}
		return matrix;
	}
	
	public static String toStringMatrix(char [][] matrix) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 27; i++) {
			for(int j = 0; j < 27; j++) {
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