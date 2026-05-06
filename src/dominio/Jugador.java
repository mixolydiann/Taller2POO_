package dominio;

import java.util.ArrayList;

public class Jugador {
    
    private String apodo;
    private int medallas;
    
    // Colecciones para manejar los Pokémon del usuario
    private ArrayList<Pokemon> equipo; // Máximo 6 Pokémon permitidos aquí
    private ArrayList<Pokemon> pc;     // El resto de Pokémon capturados van aquí
    
    // Constructor
    public Jugador(String apodo, int medallas) {
        this.apodo = apodo;
        this.medallas = medallas;
        this.equipo = new ArrayList<>();
        this.pc = new ArrayList<>();
    }
    
    public ArrayList<Pokemon> getEquipo() {
        return equipo;
    }
    
    public String getApodo() {
        return apodo;
    }
}