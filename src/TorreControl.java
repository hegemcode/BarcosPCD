/**
 * Clase que representa la torre de control encargada de coordinar los barcos que entran en el puerto.
 */
public class TorreControl {
    private static TorreControl torre;
    // Contadores: barcos que están entrando, saliendo o esperando a salir
    private int b_entrando, b_saliendo;
    private int b_esperandoSalir;

    /**
     * Constructor por defecto de la torre de control.
     */
    private TorreControl() {
        this.b_entrando = 0;
        this.b_saliendo = 0;
        this.b_esperandoSalir = 0;
    }

    /**
     * getInstance del Singleton.
     *
     * @return La única instancia de la torre de control.
     */
    public synchronized static TorreControl getInstance() {
        if (torre == null) {
            torre = new TorreControl();
        }
        return torre;
    }

    /**
     * PermisoEntrada es el protocolo de entrada a la sección crítica. El barco b pide permiso a la torre de control:
     * Si no hay ningún barco saliendo, entonces obtiene el permiso para entrar por la puerta. Debe estar sincronizado porque de no ser así,
     * dos hilos estarían accediendo a una misma variable -> entrando.
     *
     * @param b El barco que pide permiso a la torre de control.
     */
    public synchronized void permisoEntrada(Barco b) {
        System.out.println("El barco " + b.getId() + " PIDE permiso para entrar...");

        while (b_saliendo != 0 || b_esperandoSalir != 0) {
            try {
                System.out.println("El barco " + b.getId() + " esperando para entrar.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El barco " + b.getId() + " TIENE permiso para entrar...");
        System.out.println("El barco " + b.getId() + " TIENE permiso para entrar...");
        System.out.println("El barco " + b.getId() + " TIENE permiso para entrar...");
        b_entrando++;
    }

    /**
     * PermisoSalida es el protocolo de salida a la sección crítica. El barco b pide permiso a la torre de control:
     * Si no hay ningún barco entrando, entonces obtiene el permiso para entrar por la puerta. Debe estar sincronizado porque de no ser así,
     * dos hilos estarían accediendo a una misma variable -> entrando.
     *
     * @param b El barco de salida que pide permiso a la torre de control.
     */
    public synchronized void permisoSalida(Barco b) {
        System.out.println("El barco " + b.getId() + " PIDE permiso para salir...");
        while (b_entrando != 0) {
            try {
                b_esperandoSalir++;
                System.out.println("El barco " + b.getId() + " esperando para salir.");
                wait();
                b_esperandoSalir--;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El barco " + b.getId() + " TIENE permiso para salir...");
        System.out.println("El barco " + b.getId() + " TIENE permiso para salir...");
        System.out.println("El barco " + b.getId() + " TIENE permiso para salir...");
        b_saliendo++;
    }

    /**
     * Protocolo de salida. Cuando un barco acaba de entrar por la puerta, la torre de control avisa que ha terminado de entrar
     * , decrementa el número de barcos de entrada en la puerta y despierta el resto de barcos que estaban esperando debido al wait().
     * El código debe estar sincronizado para que dos barcos que terminen de entrar a la vez no accedan a la sección crítica.
     *
     * @param b El barco que ha acabado de entrar.
     */
    public synchronized void finEntrada(Barco b) {
        //System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
        //System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
        //System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
        this.b_entrando--;
        notifyAll();
    }

    /**
     * Protocolo de salida. Cuando un barco acaba de salir por la puerta, la torre de control avisa que ha terminado de salir
     * , decrementa el número de barcos de salida en la puerta y despierta el resto de barcos que estaban esperando debido al wait().
     * El código debe estar sincronizado para que dos barcos que terminen de salir a la vez no accedan a la sección crítica.
     *
     * @param b El barco que ha acabado de salir.
     */
    public synchronized void finSalida(Barco b) {
        //System.out.println("El barco " + b.getId() + " ha acabado de salir...");
        //System.out.println("El barco " + b.getId() + " ha acabado de salir...");
        //System.out.println("El barco " + b.getId() + " ha acabado de salir...");
        this.b_saliendo--;
        notifyAll();
    }
}
