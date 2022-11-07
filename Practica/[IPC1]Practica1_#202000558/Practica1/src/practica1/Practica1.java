package practica1;

import java.util.Scanner;
import static practica1.Pokemon.pokemons;
import java.util.Random;


public class Practica1 {
    //globales
    static Batalla batallasRealizadas[] = new Batalla[20]; //Arreglo de batallas para almacenarlas
    static int contadorBatallas = 0;

    static String ganadores[] = new String[20];
    static String perdedores[] = new String[20];
    
    public static void main(String[] args) {
      
        pDefecto();// inicializa al ejecutar el programa
        menu();

    }//fm   

    public static void menu() {
        Scanner tc = new Scanner(System.in);

               
        while (true) {
            System.out.println("================== MENU PRINCIPAL ==================");
            System.out.println("Iniciar sesion como: ");
            System.out.println(" 1. Administrador ");
            System.out.println(" 2. Usuario ");
            System.out.println(" 3. Salir");
            System.out.println("====================================================\n");
            String op = tc.nextLine();
            
            switch (op) {

                case "1":
                    SubM_A();
                    break;
                    
                case "2":
                    SubM_U();
                    break;

                case "3":
                    System.exit(0);
                    break;
                    
                case "":
                    System.out.println(" Por Favor, Seleccione una Opción...\n");
                    break;

                default:
                    System.out.println(" Opcion incorrecta...\n");
                    break;
            }
        }
    }

    private static void SubM_A() {

        Scanner tc = new Scanner(System.in);

        String pass;
        
        System.out.println("==================== BIENVENIDO ====================");
        System.out.print(" Ingrese su contraseña:  ");
        pass = tc.nextLine();
        System.out.println("====================================================");

        if ("202000558".equals(pass)) {
            System.out.println("    **Credenciales Validas**");
            System.out.println(" ");
            admi();

        } else {
            System.out.println(" ");
            System.out.println(" Contraseña incorrecta...");
            System.out.println(" ");
        }
        

    }

    public static void SubM_U() {
         System.out.println("=================== INICIAR JUEGO ==================\n");
        
        Scanner tc = new Scanner(System.in);
         
        //pide al usuario 1 el nombre
        String j1;//jugador 1
        System.out.print("Ingrese nombre del primer jugador: ");
        j1 = tc.nextLine().trim().toLowerCase();

        while ("".equals(j1)) {
            System.out.print("Ingrese algo, Por Favor...");
        }
        
        
        
        //pide al usuario 2 el nombre
        String j2;//jugador 2
        System.out.print("Ingrese nombre del segundo jugador: ");
        j2 = tc.nextLine().trim().toLowerCase();

        while ("".equals(j2)) {
            System.out.print("Ingrese algo, Por Favor...");
        }
        
        
        //mostrar lista pokemon segun las veces que tengan para elegir
        Pokemon poke1 = elegirPokemon(j1, 1);
        Pokemon poke2 = null;
        while (poke2 == null) {
            poke2 = elegirPokemon(j1, 2);
            if (poke2.nombre.equals(poke1.nombre)) {
                poke2 = null;
            }
        }

        Pokemon poke3 = elegirPokemon(j2, 1);
        Pokemon poke4 = null;
        while (poke4 == null) {
            poke4 = elegirPokemon(j2, 2);
            if (poke3.nombre.equals(poke4.nombre)) {
                poke4 = null;
            }
        }

        //creando los usuarios
        Usuario user1 = new Usuario(j1, poke1, poke2);
        Usuario user2 = new Usuario(j2, poke3, poke4);
        
        //inicio de la batalla
        Batalla batalla = new Batalla(user1,user2);
        batalla.iniciarJuego(); //iniciar 


        //registro de batallas
        for (int i = contadorBatallas; i < batallasRealizadas.length; i++) {//cuando finalize una batalla, recorre el arreglo y cada iteracion es una batalla realizada
            if (batallasRealizadas[i] == null) { //en el primer espacio vacio que encuentre almacena la batalla
                batallasRealizadas[i] = batalla;
                ganadores[i] = batalla.ganador;
                perdedores[i] = batalla.perdedor;
                break;
            }
        }

        System.out.println("\n\nGANADOR!!! " + batalla.ganador);
        
    }
    
    public static Pokemon elegirPokemon(String nombre, int numero) {

        //Mensaje personalizado
        System.out.println("\n\nJugador/a " + nombre + " escoja su " + numero + " pokemon");

        Scanner sc = new Scanner(System.in);
        int contadorPokemonesDisponibles = 0;

        //Condicion para que el while funcione
        int opcion = -1;
        
        System.out.println("======== Pokemones disponibles para elegir ========");
 
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] != null) {
                
                System.out.println("\n");
                System.out.println(i + "."+" Nombre: " + pokemons[i].nombre);
                System.out.println("   Vida: " + pokemons[i].vida);
                System.out.println("   Puntos Ataque: " + pokemons[i].puntosAtaque);
                System.out.println("   Representación: \n" + pokemons[i].imagen);
                System.out.println("\n");
                contadorPokemonesDisponibles++;//Cuenta los pokemones existentes
            }
        }

        while (opcion == -1) {
            System.out.println("====================================================");;
            System.out.print(" Seleccione uno de los siguientes pokemones: ");
   
            try {
                opcion = sc.nextInt();//leyendo la opcion
                sc.nextLine();
                //Verificando que la opcion seleccionada no sobrepase el limite disponible y que no sea un numero negativo
                if (opcion < 0 || opcion > contadorPokemonesDisponibles - 1) {
                    System.out.println(" Ingrese un número dentro del rango disponible... ");
                    opcion = -1;//Reiniciar la opcion para que el while siga funcionando
                } else {
                    //Cuando se cumple con una opcion valida se itera la lista de pokemones disponibles
                    for (int i = 0; i < contadorPokemonesDisponibles; i++) {
                        if (i == opcion) {
                            return pokemons[i]; //Retorna el pokemon seleccionado
                        }
                    }
                }
            } catch (Exception e) {//Error de entrada, solo se permiten numeros
                System.out.println(" Ingrese solo números por favor...");
                sc.nextLine();
            }
        }

        return null;
    }//

    public static void admi() {

        Scanner tc = new Scanner(System.in);

        while (true) {
           
            System.out.println("============== SELECCIONE UNA OPCIÓN ===============");
            System.out.println(" 1. Lista de Pokémon");
            System.out.println(" 2. Agregar Pokémon");
            System.out.println(" 3. Editar Pokémon");
            System.out.println(" 4. Reportes");
            System.out.println(" 5. Regresar al menú principal");
            System.out.println("====================================================");
            String op = tc.nextLine();

            switch (op) {

                case "1":
                    ListP();
                    break;
                case "2":
                    addP();
                    break;

                case "3":
                    aditP();
                    break;

                case "4":
                    Report();
                    break;

                case "5":
                    menu();
                    break;

                case "":
                    System.out.println(" Por Favor, Seleccione una Opción...\n");
                    break;

                default:
                    System.out.println(" Opcion incorrecta...\n");
                    break;
            }
        }
    }

    static void ListP() {
        
        System.out.println("=================== LISTA POKEMÓN ==================\n");
        //pokemones por defecto 6
               
        //pokemones agregados por el admin
        for (Pokemon pokemon : Pokemon.pokemons) {//tipo_de_dato 
            if (pokemon != null) {
                System.out.println(" Nombre: " + pokemon.nombre);
                System.out.println(" Vida: " + pokemon.vida);
                System.out.println(" Puntos Ataque: " + pokemon.puntosAtaque);
                System.out.println(" Representacion: \n" + pokemon.imagen);
                System.out.println("\n");
                
            }
            
        }
        
        Scanner tc = new Scanner(System.in);
        System.out.println(" Ingrese x para regresar ");
        String op = tc.nextLine().trim().toLowerCase();
        
        switch (op) {

            case "x":
                admi();
                break;
                
            default:
                ListaP();
                break;
        }
        
    }//f
    
    static void addP() { //agregar pokemon
       
        System.out.println("\nañadir: ");

        Scanner tc = new Scanner(System.in);

        String nombre = "";//editar nombre pokemon
        System.out.print(" Ingrese el nombre del pokemón: ");
        nombre = tc.nextLine().trim().toLowerCase();

        while ("".equals(nombre)) {
            System.out.println(" Ingrese algo, Por Favor...");
            addP();
        }

        int vida; //editar vida pokemon

        do {
            System.out.print(" Ingrese la vida del pokemón [0-100]: ");

            while (!tc.hasNextInt()) {
                String next = tc.next();
                System.out.println(next + " no es un número válido");
                addP();

            }
            vida = tc.nextInt();
            tc.nextLine();

            if (vida < 0 || vida > 100) {
                System.out.println(" El valor no se encuentra entre el rango definido...");
                addP();
            }
            break;
        } while (true);

        int ataque;//editar ataque pokemon

        do {
            System.out.print(" Ingrese puntos de ataque del pokemón [0-20]: ");

            while (!tc.hasNextInt()) {
                String next = tc.next();
                System.out.println(next + " no es un número válido");
                addP();

            }
            ataque = tc.nextInt();
            tc.nextLine();

            if (ataque < 0 || ataque > 20) {
                System.out.println(" El valor no se encuentra entre el rango definido...");
                addP();
            }
            break;
        } while (true);

        String imagen = "";//añadir imagen pokemon
        System.out.print(" Ingrese la imagen del pokemón: \n");
        imagen = tc.nextLine();

        while ("".equals(imagen)) {
            System.out.println(" Ingrese algo, Por Favor...");
            imagen = tc.nextLine();
        }
        
        
        imagen = imagen.replace("\\n", "\n"); //para remplazar un solo caracter de una cadena (oldChar, newChar) 

        Pokemon pokemonCreado = new Pokemon(nombre, vida, ataque, imagen); //instancia del ob

        if (pokemonCreado.crearPokemon()) {
            System.out.println("\n Pokémon creado................... \n");
            admi();
        } else {
            System.out.println(" El Pokémon no fue creado.................");
        }

    }

    static void aditP() {
        System.out.println("editar");
        

        String nombreP = "";
        Scanner tc = new Scanner(System.in);
        System.out.print(" Ingrese el nombre del pokémon que desea editar: ");
        nombreP = tc.nextLine().trim().toLowerCase();
        
        Pokemon pokemonE = Pokemon.buscarPokemon(nombreP);
        
        if (Pokemon.buscarPokemon(nombreP) != null) {
                        
            String nuevoN = "";//editar nuevo nombre pokemon
            System.out.print(nuevoN.toLowerCase());
            System.out.print(" Ingrese el nuevo nombre: ");
            nuevoN = tc.nextLine().trim().toLowerCase();
            
            while ("".equals(nuevoN)) {
                System.out.println(" Ingrese algo, Por Favor...");
                aditP();
            }

            int nuevaVida;//editar nueva vida pokemon

        do {
            System.out.print(" Ingrese la nuea vida del pokémon [0-100]: ");

            while (!tc.hasNextInt()) {
                String next = tc.next();
                System.out.println(next + " no es un número válido");
                aditP();

            }
            nuevaVida = tc.nextInt();
            tc.nextLine();

            if (nuevaVida < 0 || nuevaVida > 100) {
                System.out.println(" El valor no se encuentra entre el rango definido...");
                aditP();
            }
            break;
        } while (true);
            
            
            
            

            int puntosAtaque;//editar puntos ataque pokemon
            
            do {
                System.out.print(" Ingrese puntos de ataque del pokémon [0-20]: ");

                while (!tc.hasNextInt()) {
                    String next = tc.next();
                    System.out.println(next + " no es un número válido");
                    aditP();

                }
                puntosAtaque = tc.nextInt();
                tc.nextLine();

                if (puntosAtaque < 0 || puntosAtaque > 20) {
                    System.out.println(" El valor no se encuentra entre el rango definido...");
                    aditP();
                }
                break;
            } while (true);
            
            

            
            if (!nuevoN.equals("")) {
                pokemonE.nombre = nuevoN;
            }

            if (nuevaVida > 0) {
                pokemonE.vida = nuevaVida;

            }
            
            if (puntosAtaque > 0) {
                pokemonE.puntosAtaque = puntosAtaque;

            }
            
            System.out.println(" Pokemón modificado con éxito...\n");
            admi();
            
        } else {
            System.out.println(" No se encontro el pokémon " + nombreP);
            admi();
        }
    }

    static void Report() {
        bubbleSort(pokemons);
        
        Scanner tc = new Scanner(System.in);


        while (true) {
            
            System.out.println("============== SELECCIONE UNA OPCIÓN ===============");
            System.out.println(" 1. Registro de Partidas ");
            System.out.println(" 2. Lista Pokémon más utilizados ");
            System.out.println(" 3. Lista Pokémon menos utilizados");
            System.out.println(" 4. Ganadores ");
            System.out.println(" 5. Perdedores ");
            System.out.println(" 6. Regresar al menú principal ");
            System.out.println("====================================================");
            String op = tc.nextLine();

            switch (op) {

                case "1":
                    Registro();
                    break;
                    
                case "2":
                    ListaP_mas();
                    break;

                case "3":
                    ListaP_menos();
                    break;

                case "4":
                    ListaG();
                    break;

                case "5":
                    ListaP();
                    break;
                    
                case "6":
                    menu();
                    break;

                case "":
                    System.out.println(" Por Favor, Seleccione una Opción...\n");
                    break;

                default:
                    System.out.println(" Opcion incorrecta...\n");
                    break;
            }
        }

    }

    static void pDefecto() {
        //llenar el arreglo con esos pokemons al iniciar partida

        /*
        Necesitamos recorrer 6 posiciones del arreglo de pokemones y agregar un nuevo valor
         */
        
        //Nombres de pokemones
        String nombres[] = {"Butterfree", "Charmander", "Furret", "Lapras", "Cubone", "Vileplume"};

        String imagenes[] = {
"                              \n" +
"       =.               .:    \n" +
"       +     .*:         =#*  \n" +
" .-   .-    :.   ..-=-:.   =+ \n" +
" - .*-.=   :   ....  #+    *= \n" +
" :. +  :: :   ..    .:   .-#  \n" +
"  :  ...*#@%#+. ...       %-  \n" +
"   .  :%@@@@@@*          .#   \n" +
"      :@@@@@@@-         .%-   \n" +
"    .. .-*@@@@%-:::.     *    \n" +
"     .   +@@@@@*-+**+-:#*     \n" +
"        -=.+%@#@:. -:#*.      \n" +
"        .#- *@+%#- .:%.       \n" +
"          -=--..- --:         \n" +
"                              ",
"        .::.                  \n" +
"      -*%%%%%=                \n" +
"     =%%%%%%*@-               \n" +
"     *%%%%%%#@%               \n" +
"     @@%%%%%@@@.        .     \n" +
"     *%@@@@@@@*        .+*    \n" +
"       =%@@@@@+-.      #%#-   \n" +
"   -+*%%%-::+%%%%%##: :*%++   \n" +
"    ++++-    :%@*+=-  .+=*+   \n" +
"            . =%%:      %.    \n" +
"      .*-  .-==%%%=  .-%*     \n" +
"     :*%@*-=-=#%%%@@@@@+      \n" +
"     -@@@@*-::-%@@@%*=.       \n" +
"    .-=+=-      #@@*.         \n" +
"                  ..          ",
"                  ..          \n" +
"               :*%@*-:-:..    \n" +
"              ==+=*=%@@#=.    \n" +
"             :-#::++*@#       \n" +
"              -:::+-:*@:      \n" +
"    .:::..     ::::::-%*      \n" +
"    .+**###-    -:::::-%      \n" +
"      *@@@@@=   :-:::::*      \n" +
"      +@@@@@@: %#=::::-=:     \n" +
"      #@@@@@@@#*%-::::=@#     \n" +
"      +@@@@%@@@%@@%##%@%      \n" +
"       #@@@@@@@@@@@@@@@@      \n" +
"        =%@@@@@@@@@@@@@-      \n" +
"          .-+%%@@%%#%@#       \n" +
"             .::              ",
"                              \n" +
"          .-%@+:-=.           \n" +
"          =#####@@+           \n" +
"         :*###@@@:            \n" +
"         .:=+%@@+             \n" +
"             #@%              \n" +
"            -*#=.:            \n" +
"          .=#-*@@@+::         \n" +
"         :=%@@@@@@@@%-        \n" +
"        :%%#@@@%%@@@@@%.      \n" +
"      ..+#%@%@@@@@@@@@@:.     \n" +
"   -*######%@@@@@@@@@@@@@%*-. \n" +
" .*#####*==%%##%@@@@@@@@@@#:  \n" +
" .:.      =****+=:=+#%=.:::.  \n" +
"                              ",
"         .            .       \n" +
"         . ..  ...-: .=:      \n" +
"          =      =-:*%.       \n" +
"          :     %@@@@@=.      \n" +
"          :    =@#+@@##--     \n" +
"        .     .=%%#@%@@=      \n" +
"       .    +#@@@@@@#*=       \n" +
"        ::-*@@%*=@@@#:        \n" +
"         -@@@@%*#%%%*%%+.     \n" +
"        =%%%@#       :%%%*:   \n" +
"      =#*@@%@*.       %***    \n" +
"      #=-@%%%%%      +@%      \n" +
"     ...*@@@%@@%*##%@@@*      \n" +
"   :-=::.*@@#-:::. .-=+=.     \n" +
"       .  ..                  ",
"                              \n" +
"                 ::=++==-.    \n" +
"          .--==##%#%*%%#%@#-  \n" +
"       .=*%%%@@@@@@#*#%%%%%%+ \n" +
"      =%%%@@@@@@@%%#%*%%*%%%: \n" +
"    =#*#%%@%@@%@@%%%%+*%%@#.  \n" +
"  -%#%#%@%#%%#%*+%%@%%@@*:    \n" +
" :%#+%%@%+%%%%%*#%%@@%-       \n" +
" -@%%%@%%%%#+%%@@@@@@@:       \n" +
"  .=***#%%%@@@@@@@@@@@@**-    \n" +
"        :-=@@@@@@@@@@@*-:     \n" +
"        :=-:*@@@@@@@%=        \n" +
"           :=+%+:::-%+=-.     \n" +
"          .++-.      -+*=     \n" +
"                              "};
        


        for (int i = 0; i < 6; i++) { //Agregando 6 pokemones por defecto
            Pokemon auxiliar = new Pokemon(nombres[i],0,0, imagenes[i]); //creando el pokemon
            auxiliar.vida=auxiliar.vidaRandom(50,100); //generando la vida aleatoria
            auxiliar.puntosAtaque=auxiliar.ataqueRandom(5,20); //generando el ataque aleatorio
            pokemons[i] = auxiliar; //metiendo el pokemon al arreglo de pokemones
        }
        
    }

    static void Registro() {
        
          for (int i = 0; i < batallasRealizadas.length; i++) {
            if (batallasRealizadas[i] != null) {

                Batalla aux = batallasRealizadas[i];
                //pokemones que uso el j1
                System.out.println("\n Jugador 1: " + aux.jugador1.nombre);
                System.out.println(" Pokemon 1: " + aux.jugador1.pokemon1.nombre);
                System.out.println(" # ataques recibidos: " + aux.jugador1.pokemon1.ataquesRecibidos);
                System.out.println(" Pokemon 2: " + aux.jugador1.pokemon2.nombre);
                System.out.println(" # ataques recibidos: " + aux.jugador1.pokemon2.ataquesRecibidos);
                
                System.out.println("\n");
                
                //pokemones que uso el j1
                System.out.println(" Jugador 2: " + aux.jugador2.nombre);
                System.out.println(" Pokemon 1: " + aux.jugador2.pokemon1.nombre);
                System.out.println(" # ataques recibidos: " + aux.jugador2.pokemon1.ataquesRecibidos);
                System.out.println(" Pokemon 2: " + aux.jugador2.pokemon2.nombre);
                System.out.println(" # ataques recibidos: " + aux.jugador2.pokemon2.ataquesRecibidos);

                System.out.println("\n Numero de ataques realizados en la partida: " + aux.numeroAtaques);

                System.out.println(" El ganador de la batalla es: " + aux.ganador + "\n");

            }
        }
    }

    static void ListaP_mas() {

        System.out.println("\nMás seleccionados: ");
        
        for (int i = 0; i < 5; i++) {
            if (pokemons[i] != null) {
                System.out.println(pokemons[i].nombre);
                System.out.println(pokemons[i].imagen);
                System.out.println("Número de veces elegidos: "+pokemons[i].numeroDeVecesSeleccionado);
            }
        }
        System.out.println("");
    }

    static void ListaP_menos() {
        System.out.println("\nMenos seleccionados: ");
        
        int contadorP =0;
        
        for (int i = 0; i < pokemons.length; i++) {
            if(pokemons[i] !=null){
            contadorP++; //conteo de cuantos existen
            }
            
        }
        
        for (int i = contadorP - 1; i > 0; i--) {//recorre de atras hacia delante el arreglo pokemon
            if (pokemons[i] != null) {
                System.out.println(pokemons[i].nombre);
                System.out.println(pokemons[i].imagen);
                System.out.println("Número de veces elegidos: "+pokemons[i].numeroDeVecesSeleccionado);
            }
        }
        System.out.println("");
    }
    
    static void ListaG() {
        System.out.println(" Ganadores: ");
        for (int i = 0; i < ganadores.length; i++) {
            if (ganadores[i] != null) {
                System.out.println(ganadores[i]);
            }
        }
    }

    static void ListaP() {
        System.out.println(" Perdedores: ");
        for (int i = 0; i < perdedores.length; i++) {
            if (perdedores[i] != null) {
                System.out.println(perdedores[i]);
            }
        }
    }


    public static void bubbleSort(Pokemon pokemones[]) { //metodo burbuja 

        Pokemon aux;

        for (int i = 0; i < pokemones.length - 1; i++) {
            for (int j = 0; j < pokemones.length - 1; j++) {
                if (pokemones[j + 1] != null && pokemones[j] != null) {
                    if (pokemones[j].numeroDeVecesSeleccionado > pokemones[j + 1].numeroDeVecesSeleccionado) {//nos devuelve un numero
                        aux = pokemones[j];//
                        pokemones[j] = pokemones[j + 1];
                        pokemones[j + 1] = aux;
                    }
                }

            }
        }
    }





}//fc
