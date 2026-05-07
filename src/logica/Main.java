package logica;
/*
Luis Molina / 21.564.225-9 / mixolydiann
Vicente Guerra / 21.855.415-6 / nemura0
*/

import dominio.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
					System.out.println();
					menuContinuar(sc,listaZonas,pokedex);
					break;
					
				case 2:
					System.out.println("Elegiste Nueva Partida.");
					System.out.println();
					menuNuevaPartida(sc, listaZonas);
					break;
					
				case 3:
					ingame = false;
					System.out.println("Ha cerrado el programa.");
					break;
					
				default:
					System.out.println("Input no reconocido. ingresa 1, 2 o 3.");
					System.out.println();
				}
				
			} catch (InputMismatchException e) {
				System.out.println("¡Error! Debes ingresar un caracter numerico.");
				sc.nextLine();
			}
			System.out.println();
		}
		
		sc.close();
	}
	
	
	public static void menuContinuar(Scanner sc,ArrayList<Habitat> listaZonas, ArrayList<Pokemon> pokedex) {
		System.out.println("Cargando partida...");
		
		Jugador jugadorCargado = cargarPartida(pokedex);
		
		if (jugadorCargado != null) {
			System.out.println("Bienvenido de vuelta, "+ jugadorCargado.getApodo() + " !");
			
			menuPrincipalJuego(sc, jugadorCargado, listaZonas);
		}  else {
			System.out.println("No se pudo cargar la partida, cree una nueva . . .");
		}
		
	}

	public static void menuNuevaPartida(Scanner sc, ArrayList<Habitat> listaZonas) {
		System.out.print("Ingrese su apodo de jugador: ");
		String apodo = sc.nextLine();
		Jugador nuevoJugador = new Jugador(apodo, 0);
		
		// En corto esto es solo en caso de crear una nueva partida (duh) 
		// y hace este corto proceso de pedir un nombre y te instancia tu objeto jugador
		// para luego solo partir con el main menu ya avalando tu nuevo objeto jugador
		
		System.out.println("Bienvenido " + apodo + "!!");
		
		guardarPartida(nuevoJugador);
		
		menuPrincipalJuego(sc, nuevoJugador, listaZonas);
	}

	public static void menuPrincipalJuego(Scanner sc, Jugador nombreJugador, ArrayList<Habitat> listaZonas) {
		boolean jugando = true;
		
		while(jugando) {
			System.out.println(nombreJugador.getApodo() + ", que deseas hacer?");
			System.out.println();
			System.out.println("1) Revisar equipo."); // done
			System.out.println("2) Salir a capturar."); // done
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar."); // done
			System.out.println("8) Guardar y Salir."); // done
			
			System.out.println();
			System.out.print("> Ingrese opcion: ");
			System.out.println();
			
			try {
				int opcionJuego = sc.nextInt();
				sc.nextLine();
				
				switch(opcionJuego) {
				
				case 1:{
					
						if (nombreJugador.getEquipo().isEmpty()) {
							System.out.println("No hay pokemones que mostrar");
						} else {
							for (int i = 0; i < nombreJugador.getEquipo().size();i++) {
								Pokemon p = nombreJugador.getEquipo().get(i);
								
								System.out.println((i+1) + ") "+ p.getNombre() + " | Tipo : " + p.getTipo() + " | Stats totales : " + p.getSumaStats());
								System.out.println();
							}
						}
					
					break;
					}
				
				case 2:{
					Captura(sc, nombreJugador, listaZonas);
					break;
				}
				case 3:{
					accesoPC(sc, nombreJugador);
					break;
				}
				case 7:{
					guardarPartida(nombreJugador);
					break;
				}
				case 8:
					System.out.println("Guardando y volviendo al menú de inicio...");
					guardarPartida(nombreJugador);
					jugando = false;
					break;
						
				default:
					System.out.println("tamo trabajando para ud");
				}
			} catch (InputMismatchException e) {
				System.out.println("Por favor ingresa un número válido.");
				sc.nextLine();
			}
		}
	}
	
	public static void accesoPC(Scanner sc, Jugador nombreJugador) {
		ArrayList<Pokemon> allPokemons = new ArrayList<>();
		
		boolean exito = false;
		
		for (Pokemon p: nombreJugador.getEquipo()) {
			allPokemons.add(p);
		} 
		// Primero el equipo y ahora el PC
		for (Pokemon p: nombreJugador.getPc()) {
			allPokemons.add(p);
		}
		
		if (nombreJugador.getEquipo().isEmpty()) { // Como no se pueden eliminar pokemones no pongo getPC tmb en el caso q hubieran en pc y no en equipo
			System.out.println("No hay pokemones que mostrar");
		}
		
		else if (nombreJugador.getEquipo().size() < 6) {
			System.out.println("No tienes ningun pokemon dentro del PC");
		
			} else {
				
			for (int i = 0; i < allPokemons.size() ;i++) {
				
				if (i == 6) {
					System.out.println("Pokemones en PC :");
					System.out.println();
				}
				
				Pokemon p = allPokemons.get(i);
				
				System.out.println((i+1) + ") "+ p.getNombre() + " | Tipo : " + p.getTipo() + " | Stats totales : " + p.getSumaStats());
				System.out.println();
			}
			
			System.out.println("Que accion quieres tomar? :");
			System.out.println("1) Cambiar Pokemon");
			System.out.println("2) Salir");
			
			try {
				int action = sc.nextInt();
				sc.nextLine();
				
				if (action == 1) {
					System.out.println("Elige el pokemon a cambiar desde el Equipo :");
					int fromEq = (sc.nextInt() - 1);
					sc.nextLine();
					
					if (fromEq >= 0 && fromEq < 6) {
						System.out.println("Elige el pokemon a cambiar desde el PC");
						int fromPC = (sc.nextInt() - 7);
						sc.nextLine();
						
						if (fromPC >= 0 && fromPC < nombreJugador.getPc().size()) {
							
							Pokemon pokeDeEquipo = nombreJugador.getEquipo().get(fromEq);
							Pokemon pokeDePc = nombreJugador.getPc().get(fromPC);
							
							// Usamos .set() que acabo de descubrir very nice
							nombreJugador.getEquipo().set(fromEq, pokeDePc);
							nombreJugador.getPc().set(fromPC, pokeDeEquipo);
							
							exito = true;
							System.out.println("¡Cambio exitoso!");
							System.out.println();
							System.out.println(pokeDePc.getNombre() + " se ha unido a tu equipo.");
							System.out.println(pokeDeEquipo.getNombre() + " ha sido enviado al PC.");
							System.out.println();
						}
					} else {
						System.out.println("Opcion invalida.");
					}
				}
				
			} catch (InputMismatchException e) {
				
				System.out.println("Error : Debes ingresar un caracter numerico válido.");
				sc.nextLine();
			}
		}
		
	}
	
	public static Pokemon generarPokemon(Habitat zona) {
		
		double rng = Math.random();
		double probabilidadAcumulada = 0.0;
		
		// Esto fue medio raro pero cuando la probabilidad acumulada sea <= el rng se elige ese pokemon
		
		for (Pokemon p : zona.getPokemonsSalvajes()) {
			probabilidadAcumulada += p.getPorcentajeAparicion();
			
			if (rng <= probabilidadAcumulada) {
				
				// Como guarde los pokemonis desde la dex a una lista de cada zona para no arruinar esa conexion de referencias
				// clonamos el pokemon y lo retornamos asi en caso de q le cambiemos algo asi no cambia tambien en la pokedex
				
				Pokemon clone = new Pokemon(p.getNombre(), p.getHabitat(), p.getPorcentajeAparicion(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(), p.getDefensaEspecial(), p.getVelocidad(), p.getTipo());
				
				return clone;
				
			}
			
		}
		// Por si hay errror
		return null;
	}
	
	public static void Captura(Scanner sc, Jugador currentPlayer, ArrayList<Habitat> listaZonas) {
		System.out.println("Donde deseas ir a explorar?");
		System.out.println();
		System.out.println("Zonas disponibles");
		System.out.println();
		
		for (int i = 0 ; i < listaZonas.size(); i++) {
			System.out.println((i+1) + ") " + listaZonas.get(i).getNombre());
		}
		
		System.out.println((listaZonas.size() + 1) +") Volver al menu");
		System.out.println();
		System.out.println("Ingrese Zona: ");
		
		try {
			
			int opcionZona = sc.nextInt();
			sc.nextLine();
			
			if (opcionZona == listaZonas.size() + 1) {
				return;
			}
			
			else if (opcionZona >= 1 && opcionZona<= listaZonas.size()) {
				
				Habitat zonaElegida = listaZonas.get(opcionZona - 1);
				
				Pokemon pokeSalvaje = generarPokemon(zonaElegida);
				
				if (pokeSalvaje != null) {
					System.out.println("Oh!! Ha aparecido un increible " + pokeSalvaje.getNombre() + "!!");
					System.out.println();
					System.out.println("Que deseas hacer?");
					System.out.println();
					System.out.println("1) Capturar");
					System.out.println("2) Huir");
					System.out.println();
					System.out.println("Ingrese opcion: ");
					
					int accion = sc.nextInt();
					sc.nextLine();
					
					if (accion == 1) {
						intentarCaptura(currentPlayer, pokeSalvaje);
					} else {
						System.out.println("Has huido exitosamente !!");
					}
					
				}
				
			} else {
				System.out.println("");
				System.out.println("Opcion de zona no valida.");
			}
			
		}catch (InputMismatchException e) {
			System.out.println("Error : Ingresa un numero valido");
		}
		
		
	}
	
	public static void intentarCaptura(Jugador player, Pokemon salvaje) {
		
		// Vemos si ya tiene el poke en el equipo
		
		for (Pokemon p : player.getEquipo()) {
			
			if(p.getNombre().equalsIgnoreCase(salvaje.getNombre())) {
				System.out.println("Ya tienes un "+ salvaje.getNombre() + "en el Equipo");
				return;
			}
		}
		
		// Vemos si ya lo tiene pero ahora en el PC
		
		for (Pokemon p : player.getPc()) {
			
			if(p.getNombre().equalsIgnoreCase(salvaje.getNombre())) {
				System.out.println("Ya tienes un "+ salvaje.getNombre() + " en el PC !");
				return;
			}
		}
		
		// Si no estaba entonces muy bien
		System.out.println(salvaje.getNombre() + " Ha sido capturado con exito!!");
		
		// Ahora vemos donde lo ponemos si en equipo o en PC con la especificacion de los 6 primeros slots = equipo
		
		if (player.getEquipo().size() < 6) {
			player.getEquipo().add(salvaje);
			System.out.println(salvaje.getNombre() + " Ha sido enviado al equipo");
		} else {
			player.getPc().add(salvaje);
			System.out.println(salvaje.getNombre() + " Ha sido enviado al PC");
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
	
	public static void guardarPartida(Jugador player) {
		
		try {
			// False en filewriter significa sobreescribir
			BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt", false));
			
			if (player.getMedallas() == 0) {
				bw.write(player.getApodo() + ";none");
			} else {
				bw.write(player.getApodo() + ";" + player.getMedallas());
			}
			bw.newLine();
			
			// Los q el player tiene en el equipo
			for (Pokemon p : player.getEquipo()) {
				bw.write(p.getNombre() + ";" + p.getEstado());
				bw.newLine();
			}
			
			// Los q el player tiene en el PC
			for (Pokemon p : player.getPc()) {
				bw.write(p.getNombre() + ";" + p.getEstado());
				bw.newLine();
			}
			
			bw.close();
		
		
		} catch (IOException e) {
			System.out.println("Error : error al intentar cargar los datos");
		}
		
	}
	
	public static Jugador cargarPartida(ArrayList<Pokemon> pokedex) {
	    File archivo = new File("Registros.txt");
	    
	    try {
	        Scanner sc = new Scanner(archivo);
	        
	        if (sc.hasNextLine()) {
	            
	        	// Leemos solo las primeras lineas
	            String linea = sc.nextLine();
	            String[] partesJugador = linea.split(";");
	            
	            String nombre = partesJugador[0].trim();
	            String medallasTexto = partesJugador[1].trim();
	            
	            // Si dice none son 0 medallas
	            int medallasNum = 0;
	            
	            if (!medallasTexto.equalsIgnoreCase("none")) {
	                medallasNum = Integer.parseInt(medallasTexto);
	            }
	            
	            // Creamos al jugador
	            Jugador jugadorCargado = new Jugador(nombre, medallasNum);
	            
	            // Ahora los pokemones
	            while (sc.hasNextLine()) {
	                String lineaPoke = sc.nextLine();
	                String[] partesPoke = lineaPoke.split(";");
	                
	                String nombrePoke = partesPoke[0].trim();
	                String estadoPoke = partesPoke[1].trim();
	                
	                // Buscamos el match con la dex
	                for (Pokemon p : pokedex) {
	                    if (p.getNombre().equalsIgnoreCase(nombrePoke)) {
	                    	
	                        // Clonamos para no meter mano x referencias
	                        Pokemon clon = new Pokemon(p.getNombre(), p.getHabitat(), p.getPorcentajeAparicion(),p.getVida(), p.getAtaque(), p.getDefensa(),p.getAtaqueEspecial(), p.getDefensaEspecial(), p.getVelocidad(), p.getTipo());
	                        
	                        clon.setEstado(estadoPoke); // Le introducimos el estado xq todos son inicialmente instanciados vivos
	                        
	                        // Lo asignamos donde corresponda (equipo o PC)
	                        if (jugadorCargado.getEquipo().size() < 6) {
	                            jugadorCargado.getEquipo().add(clon);
	                        } else {
	                            jugadorCargado.getPc().add(clon);
	                        }
	                        break;
	                    }
	                }
	            }
	            
	            sc.close();
	            return jugadorCargado;
	        }
	        
	    } catch (FileNotFoundException e) {
	        System.out.println("No hay una partida guardada previa.");
	    } catch (Exception e) {
	        System.out.println("Error al procesar el archivo de guardado: " + e.getMessage());
	    }
	    
	    return null;
	}
	
}