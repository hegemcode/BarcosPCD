import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase que representa los barcos petroleros que heredan de la clase Barco
 */
public class BarcoPetrolero extends Barco {
    private int deposito_agua = 0;
    private int deposito_gas = 0;

    /**
     * Constructor parametrizado del petrolero. LLama al constructor parametrizado de la clase Barco.
     *
     * @param id
     * @param entrada
     * @param mercantil
     * @param petrolero
     */
    public BarcoPetrolero(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        super(id, entrada, mercantil, petrolero);
    }

    /**
     * Método que comienza al arrancar el hilo
     */
    @Override
    public void run() {
        super.run();
        try { // Llegada a la zona de carga
            ZonaCarga.getInstance().llegar(this);
        } catch (InterruptedException e) {
        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        RepostarGasTask t1 = new RepostarGasTask(this);
        RepostarAguaTask t2 = new RepostarAguaTask(this);
        executor.execute(t1);
        executor.execute(t2);
        executor.shutdown();
        try {
            executor.awaitTermination(15, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ZonaCarga.getInstance().reiniciarContadorLlegada();
        TorreControl.getInstance().permisoSalida(this);
        Puerta.getInstance().salir(this);
        TorreControl.getInstance().finSalida(this);
    }

    /**
     * Método que modifica la cantidad de agua
     *
     * @param cantidad
     */
    public void setDeposito_agua(int cantidad) {
        deposito_agua = cantidad;
    }

    /**
     * Método que modifica la cantidad de gas
     *
     * @param cantidad
     */
    public void setDeposito_gas(int cantidad) {
        deposito_gas = cantidad;
    }

    /**
     * Método que devuelve la cantidad de agua
     *
     * @return deposito_agua
     */
    public int getDeposito_agua() {
        return deposito_agua;
    }

    /**
     * Método que devuelve la cantidad de gas
     *
     * @return deposito_gas
     */
    public int getDeposito_gas() {
        return deposito_gas;
    }
}
