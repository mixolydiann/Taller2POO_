package logica;

import dominio.Gimnasio;
import dominio.Jugador;
import dominio.Pokemon;
import dominio.TablaTipos;

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
	
	/*FALTAN LOS MENUS DEL BUCLE DE COMBATE:
	- while() para que la pelea siga hasta que alguien se rinda o pierda
	- opcion de cambiar pokemon 
	- logica del alto mando
	*/
}