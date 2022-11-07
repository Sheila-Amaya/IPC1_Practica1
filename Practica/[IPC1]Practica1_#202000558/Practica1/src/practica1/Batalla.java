
package practica1;

import java.util.Scanner;


public class Batalla {

    Usuario jugador1;
    Usuario jugador2;
    String ganador; //no tienen ningun valor hasta que finalice la partida 
    String perdedor;
    int numeroAtaques;
    String turnoActual;//nombre del J con el turno actual
    

    public Batalla(Usuario jugador1, Usuario jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void iniciarJuego() {
       int n = (int) (Math.random() * (3 - 1) + 1); //Numero aleatorio 0 a 1

        System.out.println("Pokemones jugador 1: " + jugador1.pokemon1.nombre + " " + jugador1.pokemon2.nombre);
        System.out.println("Pokemones jugador 2: " + jugador2.pokemon1.nombre + " " + jugador2.pokemon2.nombre);

        //Para definir el turno
        if (n == 1) {
            turnoActual = jugador1.nombre;
        } else {
            turnoActual = jugador2.nombre;
        }
        
        jugador1.pokemon1.revivirPokemon();
        jugador1.pokemon2.revivirPokemon();

        jugador2.pokemon1.revivirPokemon();
        jugador2.pokemon2.revivirPokemon();
        
        /*
        La batalla va a continuar hasta que la vida de los dos pokemones de alguno de los dos jugadores sea cero
         */
        Scanner tc = new Scanner(System.in);
        

        //while ((jugador1.pokemon1.estaVivo || jugador1.pokemon2.estaVivo) || (jugador2.pokemon1.estaVivo || jugador2.pokemon2.estaVivo)) {
       
        while (ganador == null) { //mientras no se haya definido ningun ganador se sigue ejecutando el while
            mostrarDatos();
            
            /*
            1. Jugador atacando actualmente
            2. Pokemon con el que va atacar
            3. Pokemon al que va atacar
             */
            

            Pokemon pokemonAtacante;
            Pokemon pokemonDefensor;

            Usuario jugadorAtacante; //El del turno actual
            Usuario jugadorDefensor; //El del que no es su turno

            //Si el nombre del jugador del turno actual es igual al nombre del jugador 1
            if (turnoActual.equals(jugador1.nombre)) {//alternar los turnos
                jugadorAtacante = jugador1;
                jugadorDefensor = jugador2;
            } else {
                jugadorAtacante = jugador2;
                jugadorDefensor = jugador1;
            }
            

            pokemonAtacante = elegirPokemon(jugadorAtacante);
            System.out.println("\nPokemon atacante: "+pokemonAtacante.nombre);
            pokemonDefensor = elegirDefensor(jugadorDefensor);
            System.out.println("\n\nPokemon defensor: " + pokemonDefensor.nombre + "\n\n");

            //pokemonAtacante.ataqueRandom(); = 5 - 20
            //pokemonDefensor.recibirDano(); vida - danoRecibido;
            
            
            System.out.println("Vida del pokemon defensor: (Nombre) " + pokemonDefensor.nombre + " -> " + pokemonDefensor.vida);

            pokemonDefensor.restarVida(pokemonAtacante.ataqueRandom(5, 20));

            System.out.println("Vida del pokemon defensor despues: (Nombre) " + pokemonDefensor.nombre + " -> " + pokemonDefensor.vidaActual);
           
            
            //Para cambiar de turno, si es el nombre del jugador 1 cambia al 2 y viceversa.
            if (turnoActual.equals(jugador1.nombre)) {
                turnoActual = jugador2.nombre;
                //Sumar 1 al contador
            } else {
                turnoActual = jugador1.nombre;
            }

            if ((jugador1.pokemon1.estaVivo == false && jugador1.pokemon2.estaVivo == false)) { //si los 2 pokemones del J1 estan muertos el j2 es el ganador
                ganador = jugador2.nombre;
                perdedor = jugador1.nombre;
            } else if ((jugador2.pokemon1.estaVivo == false && jugador2.pokemon2.estaVivo == false)) {
                ganador = jugador1.nombre;
                perdedor = jugador2.nombre;
            }
            numeroAtaques++;// reporte, contabiliza el numero de ataques de cada poke
        }

        //System.out.println("El ganador es: " + ganador);
        
        
        
        
        
    }
    
    public void mostrarDatos(){
        
        System.out.println("\n\n");

        System.out.println("\t\tJUGADOR 01: " + jugador1.nombre+"\n");
        
        
        
        //pokemon 01 del jugador 01
        System.out.println(" Nombre: " + jugador1.pokemon1.nombre);
        System.out.println(" Vida: " + jugador1.pokemon1.vidaActual);
        System.out.println(" Ataque: " + jugador1.pokemon1.puntosAtaque);
        
        if (jugador1.pokemon1.estaVivo) {//muestra el estado actual del pokemon
            System.out.println(" Estado: " + "Vivo");
        } else {
            System.out.println(" Estado: " + "Muerto");
        }
        
        System.out.println(jugador1.pokemon1.imagen);
        
        //pokemon 02 del jugador 01     
        System.out.println(" Nombre: " + jugador1.pokemon2.nombre);
        System.out.println(" Vida: " + jugador1.pokemon2.vidaActual);
        System.out.println(" Ataque: " + jugador1.pokemon2.puntosAtaque);
        
         if (jugador1.pokemon2.estaVivo) {
            System.out.println(" Estado: " + "Vivo");
        } else {
            System.out.println(" Estado: " + "Muerto");
        }
        System.out.println(jugador1.pokemon2.imagen);
        
        System.out.println("\t\t\t\t\t\t\t\t TURNO: "+turnoActual+"\n");
        
        System.out.println("\t\tJUGADOR 02: " + jugador2.nombre+"\n");
        //Pokemon 01 del jugador 02
        System.out.println(" Nombre: " + jugador2.pokemon1.nombre);
        System.out.println(" Vida: " + jugador2.pokemon1.vidaActual);
        System.out.println(" Ataque: " + jugador2.pokemon1.puntosAtaque);
         
        if (jugador2.pokemon1.estaVivo) {
            System.out.println(" Estado: " + "Vivo");
        } else {
            System.out.println(" Estado: " + "Muerto");
        }
        System.out.println(jugador2.pokemon1.imagen);
        
        //pokemon 02 del jugador 02
        System.out.println(" Nombre: " + jugador2.pokemon2.nombre);
        System.out.println(" Vida: " + jugador2.pokemon2.vidaActual);
        System.out.println(" Ataque: " + jugador2.pokemon2.puntosAtaque);
        
        if (jugador2.pokemon2.estaVivo) {
            System.out.println(" Estado: " + "Vivo");
        } else {
            System.out.println(" Estado: " + "Muerto");
        }
        
        System.out.println(jugador2.pokemon2.imagen);
        
        
        System.out.println("\n\n");
        
    }
    
    public Pokemon elegirPokemon(Usuario user) { //Pokemon con el que va atacar
        
        Pokemon pokemonEscogido = null;

        Scanner tc = new Scanner(System.in);
        System.out.println(" Jugador " + turnoActual + " elija con que pokémon atacar: ");

        String nombrePokemon = tc.nextLine();

        //Si el nombre del pokemon del jugador atacante coincide con lo leído en consola
        if (user.pokemon1.nombre.equals(nombrePokemon)) {//verifica que el pokemon exista en la lista de los seleccionados
            pokemonEscogido = user.pokemon1;//si esta
            if (!pokemonEscogido.estaVivo) { //se verifica el estado
                nombrePokemon = "";
            }
        } else if (user.pokemon2.nombre.equals(nombrePokemon)) {
            pokemonEscogido = user.pokemon2;
            if (!pokemonEscogido.estaVivo) {
                nombrePokemon = "";
            }
        } else {
            nombrePokemon = "";
        }

        //Mientras que el nombre no coincida o que no se ingrese nada se seguira pidiendo valores
        while ("".equals(nombrePokemon)) {
            System.out.println("Jugador " + turnoActual + " elija con que pokemon atacar: ");
            nombrePokemon = tc.nextLine();
            if (user.pokemon1.nombre.equals(nombrePokemon)) {
                pokemonEscogido = user.pokemon1;
                if (!pokemonEscogido.estaVivo) {
                    nombrePokemon = "";
                }
            } else if (user.pokemon2.nombre.equals(nombrePokemon)) {
                pokemonEscogido = user.pokemon2;
                if (!pokemonEscogido.estaVivo) {
                    nombrePokemon = "";
                }
            } else {
                nombrePokemon = "";
            }
        }
        return pokemonEscogido;
    }

    //Pokemon al que va atacar
    public Pokemon elegirDefensor(Usuario user) {
        Pokemon pokemonEscogido = null;

        Scanner tc = new Scanner(System.in);
        System.out.println("Jugador " + turnoActual + " elija a que pokemon atacar: ");

        String nombrePokemon = tc.nextLine();

        //Si el nombre del pokemon del jugador atacante coincide con lo leído en consola
        if (user.pokemon1.nombre.equals(nombrePokemon)) {
            pokemonEscogido = user.pokemon1;
            if (!pokemonEscogido.estaVivo) {
                nombrePokemon = "";
            }
        } else if (user.pokemon2.nombre.equals(nombrePokemon)) {
            pokemonEscogido = user.pokemon2;
            if (!pokemonEscogido.estaVivo) {
                nombrePokemon = "";
            }
        } else {
            nombrePokemon = "";
        }

        //Mientras que el nombre no coincida o que no se ingrese nada se seguira pidiendo valores
        while ("".equals(nombrePokemon)) {
            System.out.println("Jugador " + turnoActual + " elija a que pokemon atacar: ");
            nombrePokemon = tc.nextLine();
            if (user.pokemon1.nombre.equals(nombrePokemon)) {
                pokemonEscogido = user.pokemon1;
                if (!pokemonEscogido.estaVivo) {
                    nombrePokemon = "";
                }
            } else if (user.pokemon2.nombre.equals(nombrePokemon)) {
                pokemonEscogido = user.pokemon2;
                if (!pokemonEscogido.estaVivo) {
                    nombrePokemon = "";
                }
            } else {
                nombrePokemon = "";
            }
        }
        return pokemonEscogido;
    }
    

    
}//fc
