package dominio;

public class Pokemon {
    
    // Atributos base (De Pokedex.txt)
    private String nombre;
    private String habitat;
    private double porcentajeAparicion;
    private int vida;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    private String tipo;
    
    // Atributo de estado (De Registros.txt)
    private String estado; // Puede ser "Vivo" o "Debilitado"
    
    // Constructor
    public Pokemon(String nombre, String habitat, double porcentajeAparicion, int vida, int ataque, 
                   int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, String tipo) {
        this.nombre = nombre;
        this.habitat = habitat;
        this.porcentajeAparicion = porcentajeAparicion;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.tipo = tipo;
        this.estado = "Vivo";
    }

	public String getHabitat() {
		return habitat;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEstado() {
		return estado;
	}

	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}

	public int getVida() {
		return vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
	public int getSumaStats() {
		return vida + ataque + defensa + ataqueEspecial + defensaEspecial + velocidad;
	}
}