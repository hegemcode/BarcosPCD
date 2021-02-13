/*
    Clase que representa los barcos que van a entrar y salir con respecto una puerta.
 */
public class Barco implements Runnable {
    private int id;
    private boolean entrada;

    /*
        Constructor parametrizado.
        @param id El ID del barco.
        @param direccion El sentido del barco.
     */
    public Barco(int id, boolean entrada) {
        this.id = id;
        this.entrada = entrada;
    }

    /*
        Devuelve el id del barco.
        @return El id del barco.
     */
    public int getId() {
        return this.id;
    }

    /*
        Método run que arranca al crear un Hilo.
     */
    public void run() {
        if (entrada) {
            Puerta.getInstance().entrar(this);
        } else
            Puerta.getInstance().salir(this);
    }
}
