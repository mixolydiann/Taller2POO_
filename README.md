# Taller 02: Simulador Pokémon 

## Contexto Académico
* **Institución:** Universidad Católica del Norte (UCN)
* **Carrera:** Ingeniería en Tecnologías de Información (ITI)
* **Asignatura:** Programación Orientada a Objetos - I Semestre 2026
* **Docentes:** Alejandro Paolini Godoy, Cristhian Rabi Reyes, Juan Nilo

## Descripción del Proyecto
Este proyecto es un menú interactivo de simulación Pokémon por consola desarrollado en Java. El sistema permite al usuario gestionar una partida persistente, explorar hábitats para capturar Pokémon (basado en probabilidades de aparición), administrar un equipo táctico y desafiar progresivamente a los Líderes de Gimnasio hasta alcanzar el reto consecutivo del Alto Mando. El motor de combate utiliza una sumatoria de estadísticas base ponderadas por una matriz de efectividad de tipos.

## Autores
* **Luis Molina** - [@mixolydiann](https://github.com/mixolydiann)
* **Vicente Guerra** - [@nemura0](https://github.com/nemura0)

---

## Arquitectura y Estructura del Código (POO)
El proyecto fue diseñado aplicando buenas prácticas de Programación Orientada a Objetos, garantizando alta cohesión, encapsulamiento y el Principio de Responsabilidad Única (SRP). Se divide en dos paquetes principales:

* **`dominio` (Modelo de Datos):**
  * `Pokemon`: Maneja atributos base, estadísticas y estado actual (Vivo/Debilitado).
  * `Jugador`: Administra las medallas, el equipo principal (máximo 6) y el almacenamiento en el PC.
  * `Gimnasio`: Estructura a los líderes (y Alto Mando) junto a sus equipos rivales y estado de derrota.
  * `Habitat`: Representa las zonas de exploración y su lista de especies salvajes.
  * `TablaTipos`: Encapsula la matriz bidimensional estática (`double[][]`) para el cálculo rápido de efectividad sin condicionales anidados.

* **`logica` (Controladores):**
  * `Main`: Punto de entrada que gestiona los menús interactivos, inicializa colecciones y maneja la carga/escritura de archivos (`Scanner`, `BufferedWriter`).
  * `SistemaBatalla`: Motor independiente que procesa la matemática de daño, turnos, relevos y el bucle consecutivo del Alto Mando.

---

## Puntos de la Pauta

### 1. Persistencia de Datos y Archivos
* **Lectura Inicial:** Se procesan correctamente `Habitats.txt`, `Pokedex.txt`, `Gimnasios.txt` y `Alto Mando.txt` al iniciar, instanciando los objetos sin caídas.
* **Sistema de Partidas (`Registros.txt`):** Se sobrescribe y lee el estado exacto del jugador (apodo, medallas, y estado individual de cada Pokémon en el Equipo/PC), permitiendo Continuar, Guardar, y Guardar/Salir sin perder el progreso.

### 2. Lógica de Colecciones
* **Uso de ArrayList:** Se implementaron colecciones dinámicas para gestionar inventarios de equipos, listas de gimnasios, catálogos de zonas y la Pokedex.
* **Matriz de Tipos:** Se implementó eficientemente la tabla de efectividad para escalar el daño (x2 o x0.5) consultando directamente índices.

### 3. Mecánicas de Juego
* **Captura:** Generación probabilística estricta según el `%` de aparición de cada zona. Prevención de capturas duplicadas en equipo y PC.
* **Combate Dinámico:** El usuario puede atacar, rendirse o cambiar de Pokémon táctico durante la batalla. El sistema bloquea el uso de Pokémon en estado `Debilitado`.
* **Progresión Lineal:** Validación matemática que impide al usuario saltarse Líderes de Gimnasio o acceder al Alto Mando sin las 8 medallas.
* **Alto Mando:** Modalidad de supervivencia que obliga a vencer a los 6 integrantes de forma consecutiva sin acceso al menú principal ni al PC.

### 4. Control de Errores y Robustez
* **Validación de Entradas:** Toda interacción de consola está protegida mediante bloques `try-catch` capturando `InputMismatchException` para evitar que el ingreso de letras rompa los menús numéricos.
* **Navegación Segura:** Menús en bucle `while` que permiten cancelar acciones o retornar sin atrapar al usuario.
* **Clean Code:** Uso de convención `CamelCase` y variables descriptivas.

---

## Entregables
Tal como se solicita en los requerimientos del taller, los siguientes documentos se encuentran en la raíz de este repositorio:
1. `DiagramaClases.pdf`
2. `ModeloDominio.pdf`

## Instrucciones de Ejecución
1. Clonar el repositorio de manera local o descargarlo.
2. Asegurarse de que los archivos de texto (`Pokedex.txt`, `Habitats.txt`, `Gimnasios.txt`, `Alto Mando.txt`, `Registros.txt`) estén ubicados en la raíz del proyecto.
3. Importar el proyecto en Eclipse.
4. Proceder con el `Main.java`.
