import java.sql.SQLOutput;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.*;

/**
 * Clase que representa la plataforma donde los barcosMercantes depositan sus contenedores y las gruas los recogen.
 */
public class Plataforma {
    private static Plataforma p;
    private String contenedor;
    private boolean Fin;
    private SynchronousQueue<String> sal;
    private SynchronousQueue<String> azucar;
    private SynchronousQueue<String> harina;
    private SynchronousQueue<String> barcoM;

    /**
     * Constructor por defecto de la única plataforma que existe en el puerto. Actua de monitor para gestionar
     * el depósito y la extracción de los contenedores entre el barco mercante y las gruas del puerto.
     */
    private Plataforma() {
        this.Fin = false;
        sal = new SynchronousQueue<>();
        azucar = new SynchronousQueue<>();
        harina = new SynchronousQueue<>();
        barcoM = new SynchronousQueue<>();
    }

    public void setFin(boolean fin) {
        Fin = fin;
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

    public String getContenedor() {
        return contenedor;
    }

    /**
     * Método en el que un barco mercante pone un contenedor en la plataforma.
     *
     * @param contenedor String con el tipo de contenedor.
     * @param b          BarcoMercante encargado de poner el contenedor.
     */
    public void put(String contenedor, BarcoMercante b) throws InterruptedException {
        this.contenedor = contenedor; // El barco deposita el contenedor
        switch (contenedor) {
            case "sal":
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() + "...");
                sal.put(contenedor);
                break;
            case "azucar":
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() + "...");
                azucar.put(contenedor);
                break;
            case "harina":
                System.out.println("El barco " + b.getId() + " DEPOSITA un contenedor de " + contenedor.toUpperCase() + "...");
                harina.put(contenedor);
                break;
        }
    }

    /**
     * Método en el que una grua extrae el contenedor de la plataforma.
     *
     * @param contenedor Tipo de contenedor que se extrae de la plataforma.
     */
    public void get(String contenedor) throws InterruptedException {
        do {
            switch (contenedor) {
                case "sal":
                    System.out.println("La grua " + contenedor.toUpperCase() + " ESPERA...");
                    sal.take();
                    System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() + "...");
                    break;
                case "azucar":
                    System.out.println("La grua " + contenedor.toUpperCase() + " ESPERA...");
                    azucar.take();
                    System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() + "...");
                    break;
                case "harina":
                    System.out.println("La grua " + contenedor.toUpperCase() + " ESPERA...");
                    harina.take();
                    System.out.println("La grua " + contenedor.toUpperCase() + " COGE un contenedor de  " + contenedor.toUpperCase() + "...");
                    break;
            }
        } while (!this.Fin);

    }
}
