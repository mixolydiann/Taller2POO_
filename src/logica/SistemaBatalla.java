package logica;

import dominio.Gimnasio;
import dominio.Jugador;
import dominio.Pokemon;
import dominio.TablaTipos;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBatalla {

	// Calcula el turno de atacar como dice el enunciado
	public static boolean ejecutarAtaque(Pokemon pokeJugador, Pokemon pokeRival) {
		
		int statsJugador = pokeJugador.getSumaStats();
		int statsRival = pokeRival.getSumaStats();
		
		double multiplicador = TablaTipos.getMultiplicador(pokeJugador.getTipo(), pokeRival.getTipo());
		double puntosFinalesJugador = statsJugador * multiplicador;
		
		System.out.println(pokeJugador.getNombre() + " -> " + statsJugador + " puntos");
		System.out.println(pokeRival.getNombre() + " -> " + statsRival + " puntos");
		System.out.println();
		
		if (multiplicador == 2.0) {
			System.out.println("¡" + pokeJugador.getNombre() + " es muy efectivo contra " + pokeRival.getNombre() + "!");
			
		} else if (multiplicador == 0.5) {
			System.out.println(pokeJugador.getNombre() + " no es efectivo contra " + pokeRival.getNombre() + "!");
		}
		
		
		System.out.println("Nuevo puntaje:");
		System.out.println(pokeJugador.getNombre() + " -> " + (int)puntosFinalesJugador + " puntos");
		System.out.println(pokeRival.getNombre() + " -> " + statsRival + " puntos");
		System.out.println();
		
		
		// Retorna true si gana el jugador, false si pierde
		if (puntosFinalesJugador > statsRival) {
			pokeRival.setEstado("Debilitado");
			System.out.println("¡Ha ganado " + pokeJugador.getNombre() + "! " + pokeRival.getNombre() + " ha sido derrotado...");
			return true;
		} else {
			pokeJugador.setEstado("Debilitado");
			System.out.println("Ha ganado " + pokeRival.getNombre() + "! " + pokeJugador.getNombre() + " ha sido derrotado...");
			return false;
		}
	}
	
	// Método principal para el ciclo de la batalla
	public static boolean iniciarCombate(Jugador jugador, Gimnasio rival, Scanner sc) {
		System.out.println("\n¡Desafiando a " + rival.getNombreLider() + "!");
		
		ArrayList<Pokemon> equipoRival = rival.getEquipoRival();
		ArrayList<Pokemon> equipoJugador = jugador.getEquipo(); 
		
		int indiceRival = 0;
		int indiceJugador = obtenerPrimerPokemonVivo(equipoJugador);
		
		if (indiceJugador == -1) {
			System.out.println("¡No tienes Pokémon vivos para pelear!");
			return false;
		}

		// El ciclo dura mientras queden rivales y al jugador le queden Pokémon vivos
		while (indiceRival < equipoRival.size() && indiceJugador != -1) {
			Pokemon pokeRival = equipoRival.get(indiceRival);
			Pokemon pokeJugador = equipoJugador.get(indiceJugador);
			
			System.out.println("\n" + rival.getNombreLider() + " saca a " + pokeRival.getNombre() + "!");
			System.out.println(jugador.getApodo() + " saca a " + pokeJugador.getNombre() + "!");
			
			boolean enCombate = true;
			while (enCombate) {
				System.out.println("\nQue deseas hacer?");
				System.out.println("1) Atacar");
				System.out.println("2) Cambiar de pokemon");
				System.out.println("3) Rendirse");
				System.out.print("Ingrese Opcion: ");
				
				int opcion = sc.nextInt();
				sc.nextLine();
				
				if (opcion == 1) {
					// Llama a la lógica de stats que hicimos en el 50% anterior
					boolean ganaJugador = ejecutarAtaque(pokeJugador, pokeRival);
					enCombate = false; 
					
					if (ganaJugador) {
						indiceRival++; // Si gana el jugador, pasa al siguiente rival
					} else {
						indiceJugador = obtenerPrimerPokemonVivo(equipoJugador); 
						if (indiceJugador == -1) {
							System.out.println("\nTe has quedado sin pokemons en tu equipo!");
						}
					}
				} else if (opcion == 2) {
					int nuevoIndice = cambiarPokemonEnBatalla(equipoJugador, sc);
					if (nuevoIndice != -1 && nuevoIndice != indiceJugador) {
						indiceJugador = nuevoIndice;
						pokeJugador = equipoJugador.get(indiceJugador);
						System.out.println("\n" + jugador.getApodo() + " cambia a " + pokeJugador.getNombre() + "!");
					}
				} else if (opcion == 3) {
					System.out.println("\nTe has rendido...");
					return false;
				} else {
					System.out.println("Opción no válida.");
				}
			}
		}
		
		if (indiceRival >= equipoRival.size()) {
			System.out.println("\n¡Has derrotado a " + rival.getNombreLider() + "!");
			rival.setEstado("Derrotado");
			return true;
		}
		
		System.out.println("Volviendo al menu...\n");
		return false;
	}

	// Busca automáticamente el primer Pokemon que no este debilitado
	public static int obtenerPrimerPokemonVivo(ArrayList<Pokemon> equipo) {
		for (int i = 0; i < equipo.size(); i++) {
			if (equipo.get(i).getEstado().equals("Vivo")) {
				return i;
			}
		}
		return -1;
	}

	// Menu secundario para cuando el usuario elige la opción 2
	public static int cambiarPokemonEnBatalla(ArrayList<Pokemon> equipo, Scanner sc) {
		System.out.println("\nTu equipo:");
		for (int i = 0; i < equipo.size(); i++) {
			System.out.println((i + 1) + ") " + equipo.get(i).getNombre() + " - Estado: " + equipo.get(i).getEstado());
		}
		System.out.println((equipo.size() + 1) + ") Cancelar");
		System.out.print("Elige un Pokemon: ");
		
		int opcion = sc.nextInt();
		sc.nextLine();
		
		if (opcion > 0 && opcion <= equipo.size()) {
			if (equipo.get(opcion - 1).getEstado().equals("Debilitado")) {
				System.out.println("¡Ese Pokemon está debilitado y no puede pelear!");
				return -1;
			}
			return opcion - 1;
		}
		return -1;
	}

	// Para el Alto Mando, recorremos la lista de forma consecutiva
	public static void retarAltoMando(Jugador jugador, ArrayList<Gimnasio> altoMando, Scanner sc) {
		System.out.println("\n¡Has entrado al desafío del Alto Mando!");
		for (Gimnasio oponente : altoMando) {
			boolean victoria = iniciarCombate(jugador, oponente, sc);
			if (!victoria) {
				System.out.println("¡Has sido derrotado! Tendrás que empezar el Alto Mando desde cero.");
				return;
			}
		}
		System.out.println("¡FELICIDADES! ¡HAS DERROTADO AL ALTO MANDO Y ERES EL NUEVO CAMPEÓN!");
	}

}