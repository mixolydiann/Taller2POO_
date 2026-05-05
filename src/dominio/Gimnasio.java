package dominio;

import java.util.ArrayList;

public class Gimnasio {
    
    private int numero;
    private String nombreLider;
    private String estado; // derrotar o derrotado
    private int cantidadPokemons;
    private ArrayList<Pokemon> equipoRival; 
    
    
    public Gimnasio(int numero, String nombreLider, String estado, int cantidadPokemons) {
        this.numero = numero;
        this.nombreLider = nombreLider;
        this.estado = estado;
        this.cantidadPokemons = cantidadPokemons;
        this.equipoRival = new ArrayList<>();
    }
    
    public void agregarPokemonRival(Pokemon pokemao){
    	equipoRival.add(pokemao);
    }

	public ArrayList<Pokemon> getEquipoRival() {
		return equipoRival;
	}
    
    
    
}