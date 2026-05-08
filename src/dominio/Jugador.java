package dominio;

import java.util.ArrayList;

public class Jugador {
    
    private String apodo;
    private int nmedallas;
    
    
    // Colecciones para manejar los Pokémon del usuario
    private ArrayList<Pokemon> equipo; // Máximo 6 Pokémon permitidos aquí
    private ArrayList<Pokemon> pc;     // El resto de Pokémon capturados van aquí
    
    // Constructor
    public Jugador(String apodo, int nmedallas) {
        this.apodo = apodo;
        this.nmedallas = nmedallas;
        this.equipo = new ArrayList<>();
        this.pc = new ArrayList<>();
    }
    
    
	public String getApodo() {
		return apodo;
	}

	public int getMedallas() {
		return nmedallas;
	}

	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	public ArrayList<Pokemon> getPc() {
		return pc;
	}

	public void setMedallas(int nmedallas) {
		this.nmedallas = nmedallas;
	}
    
	public void sumarMedalla() {
	    this.nmedallas++;
	}
	
	public void curarPokemones() {
        for (Pokemon p : equipo) {
            p.setEstado("Vivo");
        }
        for (Pokemon p : pc) {
            p.setEstado("Vivo");
        }
    }
	
}