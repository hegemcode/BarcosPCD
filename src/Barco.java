/*
    Clase que representa los barcos que van a entrar y salir con respecto una puerta.
 */
public class Barco implements Runnable {
    private int id;
    private boolean entrada;
    private boolean mercantil;

    /*
        Constructor parametrizado.
        @param id El ID del barco.
        @param direccion El sentido del barco.
        @param entrada La direccion del barco
        @param puerta La puerta
        @param torre La torre de control
     */
    public Barco(int id, boolean entrada, boolean mercantil) {
        this.id = id;
        this.entrada = entrada;
        this.mercantil = mercantil;
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
            if(mercantil) {

                // Instanciación de las tres gruas de la plataforma.
                Thread g1 = new Thread(new Grua("sal"));
                Thread g2 = new Thread(new Grua("azucar"));
                Thread g3 = new Thread(new Grua("harina"));
                g1.start();
                g2.start();
                g3.start();
            }
        } else {
            TorreControl.getInstance().permisoSalida(this);
            Puerta.getInstance().salir(this);
            TorreControl.getInstance().finSalida(this);
        }
    }
}
