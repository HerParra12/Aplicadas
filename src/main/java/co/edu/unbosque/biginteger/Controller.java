package co.edu.unbosque.biginteger;

public class Controller {

	private Modelo model;
	private Ventana view;
	
	public Controller() {
		model = new Modelo();
		view = new Ventana();
	}
	
	public static void main(String[] args) {
		Ventana view = new Ventana();
		Controller main = new Controller();
		try {
			int menu = 0;
			do {
				menu = view.leerDato("TEORIA DE NÚMEROS." +
					   "\n Selecciona la opción a realiza." +
					   "\n 1. Ingresar un numero para ver la cantidad de divisores." +
					   "\n 0. Salir.");
				
				switch(menu) {
				case 1:
					main.addNumber();
					break;
					
				case 0:
					view.mostrarInformacion("Hasta luego.");
					break;
					default:
						view.warningMessage("La opción ingresada no es valida.");
						break;
				}
			}while(menu != 0);
		} catch(Exception error) {
			view.errorMessage("Hubo un error.");
			main(args);
		}
	}
	
	private void addNumber() {
		String number;
		do {
			number = view.leerString("Ingresa el numero a evaluar.");
			if(!model.isValidNumber(number))
				view.warningMessage("El numero ingresado no es valido.");
		}while(!model.isValidNumber(number));
		view.mostrarInformacion("La cantidad de divisores del numero " + number + " es: " + model.complement(Integer.parseInt(number)));
	}
}
