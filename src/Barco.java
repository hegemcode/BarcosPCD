/**
 * Clase que representa los barcos que van a entrar y salir.
 */
public class Barco implements Runnable {
    private int id; // identificador del barco
    private boolean entrada; // indica si el barco es de entrada o salida
    private boolean mercantil; // indica si el barco es mercante
    private boolean petrolero; // indica si el barco es petrolero

    /**
     * Constructor parametrizado.
     *
     * @param id        id El ID del barco.
     * @param entrada   El sentido del barco (true si es de entrada y false en caso contrario)
     * @param mercantil Booleano que indica si es mercante o no
     * @param petrolero Booleano que indica si es petrolero o no
     */
    public Barco(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        this.id = id;
        this.entrada = entrada;
        this.mercantil = mercantil;
        this.petrolero = petrolero;
    }

    /**
     * Devuelve el id del barco.
     *
     * @return id del barco como entero.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Método que comienza al arrancar el hilo
     */
    public void run() {
        if (entrada) { // Si es un barco de entrada
            TorreControl.getInstance().permisoEntrada(this);
            Puerta.getInstance().entrar(this);
            TorreControl.getInstance().finEntrada(this);
        } else { // Si es de salida
            TorreControl.getInstance().permisoSalida(this);
            Puerta.getInstance().salir(this);
            TorreControl.getInstance().finSalida(this);
        }
    }
}
