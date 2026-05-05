package logica;
/*
Luis Molina / 21.564.225-9 / mixolydiann
Vicente Guerra / 21.855.415-6 / nemura0
*/

import dominio.*;
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
					menuContinuar(sc);
					break;
					
				case 2:
					System.out.println("Elegiste Nueva Partida.");
					menuNuevaPartida(sc);
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
	
	
	public static void menuContinuar(Scanner sc) {
		System.out.println("Cargando partida...");
		
		
		
		menuPrincipalJuego(sc, "JugadorCargado");
	}

	public static void menuNuevaPartida(Scanner sc) {
		System.out.print("Ingrese su apodo de jugador: ");
		String apodo = sc.nextLine();
		System.out.println("Bienvenido " + apodo + "!!");
		
		// Modificar Registros.txt
		
		
		menuPrincipalJuego(sc, apodo);
	}

	public static void menuPrincipalJuego(Scanner sc, String nombreJugador) {
		boolean jugando = true;
		
		while(jugando) {
			System.out.println(nombreJugador + ", que deseas hacer?");
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir.");
			System.out.print("> Ingrese opcion: ");
			
			try {
				int opcionJuego = sc.nextInt();
				sc.nextLine();
				
				switch(opcionJuego) {
					case 8:
						System.out.println("Guardando y volviendo al menú de inicio...");
						jugando = false;
						break;
						
					// revisarEquipo(), salirCapturar(), etc.
						
					default:
						System.out.println("tamo trabajando para ud");
				}
			} catch (InputMismatchException e) {
				System.out.println("Por favor ingresa un número válido.");
				sc.nextLine();
			}
		}
	}
	
	
	
	

}