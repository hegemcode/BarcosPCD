import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Clase que representan a las gruas encargadas de mover contenedores de un tipo específico.
 */
public class Grua implements Runnable {
    private String id; // ID asociado a la grua.
    private BlockingQueue<String> drop;

    /**
     * Constructor parametrizado dado el id de la grua ("mercante" o "azucar" o "harina" o "sal")
     *
     * @param id
     */
    public Grua(String id, SynchronousQueue<String> Cola) {
        this.id = id;
        this.drop = Cola;
    }

    /**
     * Método que comienza al arrancar el hilo
     */
    @Override
    public void run() {
        Plataforma.getInstance().get(id);
    }

    /**
     * Método que deposita un contenedor del barco 'b' en la plataforma.
     *
     * @param contenedor
     */
    public void put(String contenedor) {
        try {
            drop.put(contenedor);
        }catch (InterruptedException e){
            System.out.println(e.toString());
        }
    }
}
