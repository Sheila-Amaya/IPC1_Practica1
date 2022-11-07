
package practica1;

public class Pokemon {//

    public static Pokemon[] pokemons = new Pokemon[20];//min 20

//ATRIBUTOS    
    public String nombre;
    public int vida; //random 50-100 / Vida general
    public int vidaActual; //Vida en cada batalla (va disminuyendo con cada ataque recibido)
    boolean estaVivo = true; // True or false
    public int puntosAtaque; //random 5-20
    public String imagen;

    public int ataquesRecibidos;

    public int numeroDeVecesSeleccionado;

    public Pokemon(String _nombre,int _vida, int _puntosAtaque,String _imagen) {//metodo constructor: se llama siempre que se declara un objeto.
        this.nombre = _nombre;
        this.vida = _vida;
        this.imagen = _imagen;
        this.puntosAtaque = _puntosAtaque;

        this.numeroDeVecesSeleccionado = 0;//reporte
        this.ataquesRecibidos = 0;


    }
       
    boolean crearPokemon() {//metodo añadir pokemon
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] == null) { //si esta vacio
                pokemons[i] = this;
                return true;
            }

        }
        return false;//sin espacios disponibles ,no se crea
    }
    
    static Pokemon buscarPokemon(String nombre) {
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] != null) {
                if (pokemons[i].nombre.toLowerCase().equals(nombre.toLowerCase())) {
                    return pokemons[i];
                }
            }

        }
        return null;
    }

    static int vidaRandom(int menor, int mayor) {
        int vida = (int) (Math.random() * (mayor - menor) + menor);
        return vida;
    
        
    }

    static int ataqueRandom(int menor, int mayor) {// Retorna un número aleatorio entre min (incluido) y max (excluido)
        int puntosAtaque = (int) (Math.random() * (mayor - menor) + menor);
        return puntosAtaque;
        
    }
    
    public void restarVida(int numero) {
        this.ataquesRecibidos++;
        this.vidaActual = this.vidaActual - numero;
        if (this.vidaActual <= 0) {
            this.estaVivo = false;
            this.vidaActual = 0;
        }
    }
    
    void revivirPokemon() {
        this.vidaActual = this.vida;
        this.estaVivo = true;
    }
    
    void haSidoSeleccionado() {
        this.numeroDeVecesSeleccionado++;
        this.ataquesRecibidos = 0;
    }

        


}







    
