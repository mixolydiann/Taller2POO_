package logica;
/*
Luis Molina / 21.564.225-9 / mixolydiann
Vicente Guerra / 21.855.415-6 / nemura0
*/

import dominio.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		
		boolean ingame = true;
		Scanner sc = new Scanner(System.in);
		
		// Cargamos las vainas estas
		System.out.println("Iniciando sistema. . .");
		ArrayList<Pokemon> pokedex = cargarPokedex();
		ArrayList<Habitat> listaZonas = cargarHabitats();
		poblarHabitats(pokedex, listaZonas);
		ArrayList<Gimnasio> listaGimnasios = cargarGimnasios(pokedex);
		ArrayList<Gimnasio> listaAltoMando = cargarAltoMando(pokedex);
		
		
		
		
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
	
	public static ArrayList<Pokemon> cargarPokedex() {
        ArrayList<Pokemon> listaPokedex = new ArrayList<>();
        
        try {
            
            File archivo = new File("Pokedex.txt"); 
            Scanner sc = new Scanner(archivo);
            
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                
                String[] partes = linea.split(";");
                
                String nombre = partes[0];
                String habitat = partes[1];
                double probAparicion = Double.parseDouble(partes[2]);
                int vida = Integer.parseInt(partes[3]);
                int ataque = Integer.parseInt(partes[4]);
                int defensa = Integer.parseInt(partes[5]);
                int ataqueEspecial = Integer.parseInt(partes[6]);
                int defensaEspecial = Integer.parseInt(partes[7]);
                int velocidad = Integer.parseInt(partes[8]);
                String tipo = partes[9];
                
                
                Pokemon nuevoPokemon = new Pokemon(nombre, habitat, probAparicion, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, tipo);
                
                
                listaPokedex.add(nuevoPokemon);
            }
            
            sc.close();
            System.out.println("Pokédex cargada con éxito.");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo Pokedex.txt");
        }
        
        return listaPokedex;
    }
	
	public static ArrayList<Habitat> cargarHabitats() {
		ArrayList<Habitat> listaZonas = new ArrayList<>();
		
		try {
			
			File archivo = new File("Habitats.txt");
			Scanner sc = new Scanner(archivo);
			
			while (sc.hasNextLine()) {
				
				String nombreZona = sc.nextLine().trim();
				
				
				if(!nombreZona.isEmpty()) {
					Habitat nuevaZona = new Habitat(nombreZona);
					listaZonas.add(nuevaZona);
				}
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: No se encontró el archivo Habitats.txt");
		}
		
		return listaZonas;
	}
	
	public static void poblarHabitats(ArrayList<Pokemon> pokedex, ArrayList<Habitat> zonas) {
		
		for (Pokemon p : pokedex) {
			
			if (p.getHabitat().equalsIgnoreCase("none")) {
				continue;
			}
			
			for (Habitat zona : zonas) {
				if (p.getHabitat().equalsIgnoreCase(zona.getNombre())) {
					zona.agregarPokemon(p);
					break;
				}
			}
		}
	}
	
	public static ArrayList<Gimnasio> cargarGimnasios(ArrayList<Pokemon> pokedex) {
		ArrayList<Gimnasio> listaGimnasios = new ArrayList<>();
		
		File archivo = new File("Gimnasios.txt");
		
		try {
			Scanner sc = new Scanner(archivo);
			
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String [] partes = linea.split(";");
				
				int numerogym = Integer.parseInt(partes[0].trim());
				String lider = partes[1].trim();
				String estado = partes[2].trim();
				int pokecant = Integer.parseInt(partes[3].trim());
				
				// Instanciamos el gimnasio pero todavia no los pokemoners
				Gimnasio nuevoGimnasio = new Gimnasio(numerogym, lider, estado, pokecant);
				
				// Empezamos en 4 porque ahi parten los nombres
				for (int i = 4; i < partes.length; i++) {
					
					// Buscamos el match
					for (int j = 0; j < pokedex.size(); j++) {
						
						
						if (partes[i].trim().equalsIgnoreCase(pokedex.get(j).getNombre())) {
							
							// Ahora le llenamos el array d los pokemones
							nuevoGimnasio.agregarPokemonRival(pokedex.get(j));
							
							break;
						}
					}
				}
				
				listaGimnasios.add(nuevoGimnasio);
			}
			
			sc.close();
			System.out.println("Gimnasios cargados exitosamente.");
			
			return listaGimnasios;
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: No se encontro el archivo Gimnasios.txt");
			return new ArrayList<>(); // Retronamos una lista vacia para que no explote todo
		}
	}
	
	public static ArrayList<Gimnasio> cargarAltoMando(ArrayList<Pokemon> pokedex){
		ArrayList<Gimnasio> listaAltoMando = new ArrayList<>();
		File arkivo = new File("Alto Mando.txt");
		
		
		try {
			Scanner sc = new Scanner(arkivo);
			
			while(sc.hasNextLine()) {
				String linea = sc.nextLine();
				String [] partes = linea.split(";");
				
				int numero = Integer.parseInt(partes[0].trim());
				String nombre = partes[1].trim();
				
				
				Gimnasio nuevoAltoMando = new Gimnasio(numero, nombre, "Sin derrotar", 6);
				
				// Empezamos en 2 xq desde ahi estan los nombres de los pokemones
				for (int i = 2; i < partes.length; i++) {
					
					for (int j = 0; j < pokedex.size(); j++) {
						
						if (partes[i].trim().equalsIgnoreCase(pokedex.get(j).getNombre())) {
							nuevoAltoMando.agregarPokemonRival(pokedex.get(j));
							break;
						}
						
					}
					
				}
				// Despues de encontrar los matches añadimos la instancia al array de los alto mandos
				listaAltoMando.add(nuevoAltoMando);
				
			}
			sc.close();
			return listaAltoMando;
			
		} catch (FileNotFoundException e) {
			System.out.println("Error : Archivo Alto Mando.txt no encontrado");
			// y denuevo retornamos una lista vacia para q no explote el programa
			return new ArrayList<>();
		}
	}
	
}