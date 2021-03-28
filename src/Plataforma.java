import java.sql.SQLOutput;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.*;

/**
 * Clase que representa la plataforma donde los barcosMercantes depositan sus contenedores y las gruas los recogen.
 */
public class Plataforma {
    private static Plataforma p;
    private int capacidad = 1; // Indica que solo cabe un contenedor en la plataforma.
    private boolean barcoSinContenedores; // Indica si quedan o no barcos por depositar sus contenedores.
    private BlockingQueue<String> sal = new SynchronousQueue<String>();
    private BlockingQueue<String> azucar = new SynchronousQueue<String>();
    private BlockingQueue<String> harina = new SynchronousQueue<String>();

    Lock monitor = new ReentrantLock();
    Condition sal_grua = monitor.newCondition();
    Condition azucar_grua = monitor.newCondition();
    Condition harina_grua = monitor.newCondition();
    Condition espera_barco = monitor.newCondition();

    /**
     * Constructor por defecto de la única plataforma que existe en el puerto. Actua de monitor para gestionar
     * el depósito y la extracción de los contenedores entre el barco mercante y las gruas del puerto.
     */
    private Plataforma() {
        this.barcoSinContenedores = false;
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
    public SynchronousQueue<String> getDrop(){
        return (SynchronousQueue<String>) Drop;
    }
    /**
     * Método en el que un barco mercante pone un contenedor en la plataforma.
     *
     * @param contenedor String con el tipo de contenedor.
     * @param b          BarcoMercante encargado de poner el contenedor.
     */
    public void put(String contenedor, BarcoMercante b) throws InterruptedException {
            if (capacidad == 0) {
                try {
                    espera_barco.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            capacidad--;
            //System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            //System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            //System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            switch (contenedor) {
                case "sal":
                    sal.take();
                    b.reducirContenedor(1);
                    break;
                case "azucar":
                    azucar.take();
                    b.reducirContenedor(0);
                    break;
                case "harina":
                    harina.take();
                    b.reducirContenedor(2);
                    break;
            }
            if (b.numeroContenedores() == 0) {
                barcoSinContenedores = true;
                sal.take();
                harina.take();
                azucar.take();
            }
    }

    /**
     * Método en el que una grua extrae el contenedor de la plataforma.
     *
     * @param contenedor Tipo de contenedor que se extrae de la plataforma.
     */
    public void get(String contenedor) {
        String cont;
        try{
            while(!((cont = Drop.take()).equals("DONE"))){
                switch (cont){
                    case "sal":
                        sal_grua.await();
                        break;
                    case "azucar":
                        azucar_grua.await();
                        break;
                    case "harina":
                        harina_grua.await();
                        break;
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
