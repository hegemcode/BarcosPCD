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
    /*
        Entrar de barco
     */
    public synchronized void entrarBarco() {
        while (!torre.permisoEntrada(this)) {
            try {
                System.out.println("ESPEROOOOOOOOOOOOOOOOO");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        puerta.entrar(this);
        torre.incEntrando(); // Aumenta el contador de barcos que estan entrando
        torre.finEntrada(this);
    }

    /*
        Salir de barco
     */
    public synchronized void salirBarco() {
        while (!torre.permisoSalida(this)) {
            try {
                System.out.println("ESPEROOOOOOOOOOOOOOOOO");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        puerta.salir(this);
        torre.incSaliendo(); // Aumenta el contador de barcos que estan entrando
        torre.finSalida(this);
    }

    /*
        Método run que arranca al crear un Hilo.
     */
    public void run() {
        if (entrada) {
            /*synchronized (this) {
                while (!torre.permisoEntrada(this)) {
                    try {
                        System.out.println("ESPEROOOOOOOOOOOOOOOOOO");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                puerta.entrar(this);
                torre.incEntrando(); // Aumenta el contador de barcos que estan entrando
                torre.finEntrada(this);
            }*/
            entrarBarco();

        } else {
            /*synchronized (this) {
                while (!torre.permisoSalida(this)) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                puerta.salir(this);
                torre.incSaliendo(); // Aumenta el contador de barcos que estan entrando
                torre.finSalida(this);
            }
             */
            salirBarco();
        }
    }
}
