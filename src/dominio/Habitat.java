package dominio;

import java.util.ArrayList;

public class Habitat {
	
	private String nombre;
	private ArrayList<Pokemon> pokemonsSalvajes;
	
	public Habitat(String nombre) {
		this.nombre = nombre;
		this.pokemonsSalvajes = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Pokemon> getPokemonsSalvajes() {
		return pokemonsSalvajes;
	}


	public void agregarPokemon(Pokemon p) {
		this.pokemonsSalvajes.add(p);
	}
	
	
	
}
