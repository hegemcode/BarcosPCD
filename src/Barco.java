/*
    Clase que representa los barcos que van a entrar y salir con respecto una puerta.
 */
public class Barco implements Runnable {
    private int id;
    private boolean entrada;
    private Puerta puerta;

    /*
        Constructor parametrizado.
        @param id El ID del barco.
        @param direccion El sentido del barco.
        @param puerta La puerta por la que pasa el barco.
     */
    public Barco(int id, boolean entrada, Puerta puerta) {
        this.id = id;
        this.entrada = entrada;
        this.puerta = puerta;
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
            puerta.entrar(this);
        } else
            puerta.salir(this);
    }
}
