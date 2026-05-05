package logica;
/*
Luis Molina / 21.564.225-9 / mixolydiann
Vicente Guerra / 21.855.415-6 / nemura0
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		
		boolean ingame = true;
		
		Scanner sc = new Scanner(System.in);
		
		while(ingame) {
			
			System.out.println("1) Continuar.");
			System.out.println("2) Nueva partida.");
			System.out.println("3) Salir");
			System.out.println();
			System.out.print("> Ingrese opcion: "); 
			
			try {
				
				int mainm = sc.nextInt();
				sc.nextLine(); 
				
				switch(mainm) {
				case 1:
					System.out.println("Elegiste Continuar.");
					break;
				case 2:
					System.out.println("Elegiste Nueva Partida.");
					break;
				case 3:
					ingame = false;
					System.out.println("Ha cerrado el programa.");
					break;
				default:
					System.out.println("Input no reconocido. ingresa 1, 2 o 3.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("¡Error! Debes ingresar un caracter numerico.");
				sc.nextLine(); 
			}
			System.out.println(); 
		}
		
		
		sc.close();
	}

}