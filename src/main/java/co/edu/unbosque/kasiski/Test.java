/*package co.edu.unbosque.kasiski;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class Main {

	private static int counter = 0;	
	private static int maximoComun = 0;
	
	public Main() {}
	
	
	public static void group(String message) {
		String newString = message.toUpperCase().replaceAll(" ", "").replaceAll(":", "");
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
			if(key.length() > 3 && value > 1) {                 // LUEGO CAMBIAR ESTOS PARAMETROS PARA QUE LA LONGITUD MINIMAS SEA 3
				mapping.put(key, value);
			}
		});
		
		mapping.forEach((key, value) -> {
			if(!mappingList.containsKey(key)) {
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
		
		System.out.println("Maximo comun = " + maximoComun);
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
		String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "I", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		listString.forEach(string -> {
			Map <String, Long> mappingLetters = string.lines()
													  .flatMap(x -> Stream.of(x.split("")))
													  .collect(Collectors.groupingBy(key -> key.toString(), Collectors.counting()));
			for(String letter : letters) 
				if(!mappingLetters.containsKey(letter))
					mappingLetters.put(letter, 0L);
			mappingOcurrences.put(string, mappingLetters);
		});
	
//		mappingOcurrences.forEach((string, mp) -> {
//			System.out.println(string);
//			mp.forEach((k, v) -> System.out.println("Key = " + k + ", value = " + v));
//		});	
		
		// STRING PARTICIONADO && MAP <CARACTER, OCURRENCIAS> 
		List <String> listFinal = new ArrayList <> ();
		mappingOcurrences.forEach((string, mapOcurrences) -> {
			Map <String, Long> mapFinal = limitMapping(mapOcurrences);
			mapFinal.forEach((key, value) -> {
				int position = (char) key.toCharArray()[0];
				System.out.println("key = " + key + ", position = " + position);
				
			});
		});
	}
	private static int nose = 0;
	
	
	public static void main(String[] args) {
		//Main.group("necesito el mar porque me enseña no se si aprendo musica o coinciencia: no se si es ola sola o profundo o solo ronca voz o deslumbrante suposicion de peces y navios");
		//                1        2           3                 4                       5                     6                        7           8                 9                  10             11            12        13                           14          15          16      17      18 
		//Main.group("El agente silente se occidente seguira al frente de los puntos calientes en el extremo oriente mientras alli haya gente que aliente que este valiente siga alli presente que no ausente aunque asiente con entereza que sea de forma aparente atentamente el teniente vicente morente");
		Main.group("PBVRQ VICAD SKAÑS DETSJ PSIED BGGMP SLRPW RÑPWY EDSDE ÑDRDP CRCPQ MNPWK"
				+ "UBZVS FNVRD MTIPW UEQVV CBOVN UEDIF QLONM VNUVR SEIKA ZYEAC EYEDS ETFPH"
				+ "LBHGU ÑESOM EHLBX VAEEP UÑELI SEUEF WHUNM CLPQP MBRRN BPVIÑ MTIBV VEÑIC"
				+ "ANSJA MTJOK MDODS ELPWI UFOZM QMVNF OHASE SRJWR SFQCO TWVMB JGRPW VSUEX"
				+ "INQRS JEUEM GGRBD GNNIL AGSJI DSVSU EEINT GRUEE TFGGM PORDF OGTSS TOSEQ"
				+ "OÑTGR RYVLP WJIFW XOTGG RPQRR JSKET XRNBL ZETGG NEMUO TXJAT ORVJH RSFHV" 
				+ "NUEJI BCHAS EHEUE UOTIE FFGYA TGGMP IKTBW UEÑEN IEEU");
		System.out.println("\n\n----------\n\n");
	}
	
	
	
	public static List <Long> limitElements(Map <String, Long> map, int limit){
		return map.entrySet()
				  .stream()
				  .map(entry -> entry.getValue())
				  .sorted()
				  .limit(limit)
				  .toList();
	}
	
	public static Map <String, Long> limitMapping(Map <String, Long> map){
		return map.entrySet()
				  .stream()
				  .sorted((x,y) -> y.getValue().compareTo(x.getValue()))
				  .limit(maximoComun)
				  .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
	}
	
	
	public static String maxString(Map <String, Long> map) {
		return map.entrySet()
				  .stream()
				  .max((x,y) -> x.getValue().compareTo(y.getValue()))
				  .map(string -> string.getKey())
				  .get();
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
}package co.edu.unbosque.kasiski;

public class Test {

}
*/
