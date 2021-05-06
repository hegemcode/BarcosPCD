import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Clase que representa la zona de carga donde los petroleros repostan gas y agua
 */
public class ZonaCarga {
    private static ZonaCarga z;
    private int contenedorAgua;
    private int[] contenedores_gas = new int[5];
    private Semaphore[] llegada = new Semaphore[5];
    private Semaphore[] repostar = new Semaphore[5];
    private Semaphore[] coger = new Semaphore[5];
    private Semaphore mutex;
    ArrayList<BarcoPetrolero> listaBarcos;
    int contadorLlegada = 0;
    private Thread reponedor = new Thread(new Reponedor());

    /**
     * Constructor por defecto de la zona de carga
     */
    private ZonaCarga() {
        mutex = new Semaphore(1);
        this.contenedorAgua = 1000000;
        listaBarcos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            llegada[i] = new Semaphore(0);
            coger[i] = new Semaphore(0);
            repostar[i] = new Semaphore(0);
        }
        for (int i = 0; i < 5; i++) {
            contenedores_gas[i] = 1000;
        }
        reponedor.start();
    }

    /**
     * getInstance del Singleton.
     *
     * @return la instancia de zonaCarga.
     */
    public synchronized static ZonaCarga getInstance() {
        if (z == null) {
            z = new ZonaCarga();
        }
        return z;
    }

    /**
     * Método que gestiona la llegada de los barcos de la zona de carga de manera que no pueden empezar a repostar hasta que no han llegado los 5.
     * A su vez controla que solo pueda haber 5 barcos, si hubieran 6, el último en llegar tendría que esperar.
     *
     * @param b El barco que llega a la zona de carga
     * @throws InterruptedException
     */
    public void llegar(BarcoPetrolero b) throws InterruptedException {
        mutex.acquire();
        listaBarcos.add(contadorLlegada, b); // Guardamos el depósito de gas al que va asociado el barco
        contadorLlegada++;
        if (contadorLlegada != 5) { // Caso de no haber llegado los 5 barcos
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            mutex.release();
            llegada[contadorLlegada - 1].acquire();
        } else { // Si han llegado los 5 barcos liberamos a los 4 que estaban esperando
            mutex.release();
            for (int i = 0; i < contadorLlegada - 1; i++) {
                llegada[i].release();
            }
        }

        // Repostar Gas
        while (b.getDeposito_gas() < 3000) {
            repostarGas(b);
        }
        // Repostar Agua
        repostarAgua(b);

        // Reiniciamos el contador de barcos para los próximos 5 petroleros.
        if (contadorLlegada == 5) {
            contadorLlegada = 0;
        }

    }

    /**
     * Método que reposta el gas del contenedor de un petrolero.
     *
     * @param p
     * @throws InterruptedException
     */
    public void repostarGas(BarcoPetrolero p) throws InterruptedException {
        if (contenedores_gas[listaBarcos.indexOf(p)] > 0) { // Mientras el depósito no esté vacio se rellena
            p.setDeposito_gas(p.getDeposito_gas() + 1000);
            System.out.println("Petrolero " + p.getId() + " repone GAS [" + p.getDeposito_gas() + "/3000]...");
            contenedores_gas[listaBarcos.indexOf(p)] -= 1000;
        }
        if (contenedores_gas[listaBarcos.indexOf(p)] == 0) { // Si el depósito se llena, bloqueamos el proceso de coger y despertamos al proceso reponedor
            System.out.println("El barco petrolero " + p.getId() + " ESPERA PARA REPONER GAS...");
            repostar[listaBarcos.indexOf(p)].release();
            coger[listaBarcos.indexOf(p)].acquire();
        }
    }

    /**
     * Método que repone el gas de los depósitos de la plataforma.
     *
     * @throws InterruptedException
     */
    public void rellenarGas() throws InterruptedException {
        for (int i = 0; i < 5; i++) { // Comprobamos que todos los depósitos están vacíos
            System.out.println("Esperando a que se vacien todos los depósitos");
            repostar[i].acquire();
        }
        for (int i = 0; i < 5; i++) { // Rellena cada depósito y despierta el hilo del petrolero asociado.
            System.out.println("===RELLENANDO CONTENEDOR..." + i + "===");
            contenedores_gas[i] = 1000;
            coger[i].release();
        }

    }

    /**
     * Método que reposta el agua del contenedor de un petrolero
     *
     * @param b
     */
    public void repostarAgua(BarcoPetrolero b) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (b.getDeposito_agua() < 5000) {
            b.setDeposito_agua(b.getDeposito_agua() + 1000);
            System.out.println("Petrolero " + b.getId() + " repone AGUA [" + b.getDeposito_agua() + "/5000]...");
        }
        mutex.release();
    }
}