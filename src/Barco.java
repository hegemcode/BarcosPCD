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
        @param entrada La direccion del barco
        @param puerta La puerta
        @param torre La torre de control
     */
    public Barco(int id, boolean entrada) {
        this.id = id;
        this.entrada = entrada;
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
