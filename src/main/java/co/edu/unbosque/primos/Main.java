package co.edu.unbosque.primos;

/**
 * 
 * @author Hernan Alvarado
 * @author Kevin Pinzon
 * @version 1.0
 * @since 2022
 *
 */
public class Main {

	private static Modelo model = new Modelo();
	private static Ventana view = new Ventana();
	
	public static void main(String[] args) {
		try {
			int menu = 0;
			do {
				menu = view.leerDato("TALLER NÚMEROS PRIMOS." + 
			           "\n Selecciona la opción a realizar." +
					   "\n 1. verificar si un numero es primos." + 
			           "\n 0. Salir.");
				switch(menu) {
				case 1:
					testNumber();
					break;
					
				case 0:
					view.mostrarInformacion("Hasta luego.");
					break;
					default:
						view.warningMessage("La opción ingresada no es valida.");
						break;
				
				}
			}while(menu != 0);
		}catch(Exception error) {
			view.errorMessage("Hubo un error.");
			main(args);
		}
	}
	
	private static void testNumber() {
		String number = "";
		do {
			number = view.leerString("Ingresa el numero a evaluar (numero > 0 ).");
			if(!model.isValidNumber(number))
				view.warningMessage("El numero ingresado no es valido.");
		}while(!model.isValidNumber(number));
		view.mostrarInformacion("El numero ingresado es primos? " + model.isPrime(number));
	}
}
