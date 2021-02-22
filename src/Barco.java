/*
    Clase que representa los barcos que van a entrar y salir con respecto una puerta.
 */
public class Barco implements Runnable {
    private int id;
    private boolean entrada;
    Puerta puerta;
    private TorreControl torre;

    /*
        Constructor parametrizado.
        @param id El ID del barco.
        @param direccion El sentido del barco.
        @param entrada La direccion del barco
        @param puerta La puerta
        @param torre La torre de control
     */
    public Barco(int id, boolean entrada, Puerta puerta, TorreControl torre) {
        this.id = id;
        this.entrada = entrada;
        this.torre = torre;
        this.puerta = puerta;
    }

    /*
        Devuelve el id del barco.
        @return El id del barco como entero.
     */
    public int getId() {
        return this.id;
    }
    public TorreControl getTorre(){return this.torre;}

    /*
        Método run que arranca al crear un Hilo.
     */
    public void run() {
        if (entrada) {
            getTorre().permisoEntrada(this);
            this.puerta.entrar(this);
            getTorre().finEntrada(this);
        } else {
            getTorre().permisoSalida(this);
            this.puerta.salir(this);
            getTorre().finSalida(this);
        }
    }
}
