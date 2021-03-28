import java.sql.SQLOutput;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.*;

/**
 * Clase que representa la plataforma donde los barcosMercantes depositan sus contenedores y las gruas los recogen.
 */
public class Plataforma {
    private static Plataforma p;
    private String contenedor;
    private boolean barcoSinContenedores; // Indica si quedan o no barcos por depositar sus contenedores.

    Lock monitor = new ReentrantLock();
    Lock monitor2 = new ReentrantLock();

    Condition sal_grua = monitor.newCondition();
    Condition azucar_grua = monitor.newCondition();
    Condition harina_grua = monitor.newCondition();
    Condition espera_barco = monitor.newCondition();
    private SynchronousQueue<String> sal;
    private SynchronousQueue<String> azucar;
    private SynchronousQueue<String> harina;
    private SynchronousQueue<String> barcoM;

    /**
     * Constructor por defecto de la única plataforma que existe en el puerto. Actua de monitor para gestionar
     * el depósito y la extracción de los contenedores entre el barco mercante y las gruas del puerto.
     */
    private Plataforma() {
        this.barcoSinContenedores = false;
        sal = new SynchronousQueue<>();
        azucar = new SynchronousQueue<>();
        harina = new SynchronousQueue<>();
        barcoM = new SynchronousQueue<>();
    }

    /**
     * GetInstace de la plataforma (Singleton). Si la plataforma no está instanciada, la instancia llamando al constructor privado
     * y la devuelve. En caso de que esté instanciada, la devuelve.
     *
     * @return La única plataforma instanciada.
     */
    public synchronized static Plataforma getInstance() {
        if (p == null) {
            p = new Plataforma();
        }
        return p;
    }

    /**
     * Método en el que un barco mercante pone un contenedor en la plataforma.
     *
     * @param contenedor String con el tipo de contenedor.
     * @param b          BarcoMercante encargado de poner el contenedor.
     */
    public void put(String contenedor, BarcoMercante b) throws InterruptedException {

        this.contenedor = contenedor; // El barco deposita el contenedor
        switch (this.contenedor) {
            case "sal":
                sal.put(this.contenedor);
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() +  "...");
                break;
            case "azucar":
                azucar.put(this.contenedor);
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() +  "...");
                break;
            case "harina":
                harina.put(this.contenedor);
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() +  "...");
                break;
        }

        barcoM.take();

    }

    /**
     * Método en el que una grua extrae el contenedor de la plataforma.
     *
     * @param contenedor Tipo de contenedor que se extrae de la plataforma.
     */
    public void get(String contenedor) throws InterruptedException {

        switch (contenedor) {
            case "sal":
                sal.take();
                System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() +  "...");
                break;
            case "azucar":
                azucar.take();
                System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() +  "...");                azucar.take();
                break;
            case "harina":
                harina.take();
                System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() +  "...");                harina.take();
                break;
        }
        barcoM.put("nothing");

    }
}
