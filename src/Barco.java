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
     */
    public Barco(int id, boolean entrada, Puerta puerta, TorreControl torre) {
        this.id = id;
        this.entrada = entrada;
        this.torre = torre;
        this.puerta = puerta;
    }

    /*
        Devuelve el id del barco.
        @return El id del barco.
     */
    public int getId() {
        return this.id;
    }
    public TorreControl getTorre(){return this.torre;}

    /*
        Entrar de barco
     */
    public void entrarBarco() {
       getTorre().permisoEntrada(this);
       this.puerta.entrar(this);
       getTorre().finEntrada(this);
    }

    /*
        Salir de barco
     */
    public void salirBarco() {
        getTorre().permisoSalida(this);
        this.puerta.salir(this);
        getTorre().finSalida(this);
    }

    /*
        Método run que arranca al crear un Hilo.
     */
    public void run() {
        if (entrada) {
            entrarBarco();

        } else {
            salirBarco();
        }
    }
}
