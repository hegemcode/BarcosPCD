/*
    Clase que representa los barcos que van a entrar y salir con respecto una puerta.
    Atributos:
    - id: el identificador del barco
    - entrada: booleano que indica si el barco es de entrada o salida
    - mercantil: booleano que indica si el barco es mercante
    - petrolero: booleano que indica si el barco es petrolero
 */
public class Barco implements Runnable {
    private int id;
    private boolean entrada;
    private boolean mercantil;
    private boolean petrolero;

    /*
        Constructor parametrizado.
        @param id El ID del barco.
        @param entrada El sentido del barco (true si es de entrada y false en caso contrario)
        @param mercantil Booleano que indica si es mercante o no
        @param petrolero Booleano que indica si es petrolero o no
     */
    public Barco(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        this.id = id;
        this.entrada = entrada;
        this.mercantil = mercantil;
        this.petrolero = petrolero;
    }

    /*
        Devuelve el id del barco.
        @return El id del barco como entero.
     */
    public int getId() {
        return this.id;
    }

    /*
        Método run que arranca al crear un Hilo.
     */
    public void run() {
        if (entrada) {
            TorreControl.getInstance().permisoEntrada(this);
            Puerta.getInstance().entrar(this);
            TorreControl.getInstance().finEntrada(this);
        } else {
            TorreControl.getInstance().permisoSalida(this);
            Puerta.getInstance().salir(this);
            TorreControl.getInstance().finSalida(this);
        }
    }
}
