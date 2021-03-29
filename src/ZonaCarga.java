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
    private Semaphore mutex;
    ArrayList<BarcoPetrolero> listaBarcos;
    int contadorLlegada = 0;
    private CyclicBarrier repostar;
    int contadorLlegadaPetroleros = 0;
    private Phaser phaserLlegada = new Phaser(5);
    private Phaser phaserSalida = new Phaser(5);
    /**
     * Constructor por defecto de la zona de carga
     */
    private ZonaCarga() {
        this.repostar = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                try {
                    rellenarGas();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mutex = new Semaphore(1);
        this.contenedorAgua = 1000000;
        listaBarcos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            contenedores_gas[i] = 1000;
        }
    }

    public Phaser getPhaserLlegada() {
        return phaserLlegada;
    }

    public Phaser getPhaserSalida() {
        return phaserSalida;
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
        System.out.println("El barco " + b.getId() + " ESPERA para entrar.");
        contadorLlegadaPetroleros++;
        mutex.release();
        ZonaCarga.getInstance().getPhaserSalida().awaitAdvance(contadorLlegadaPetroleros/5);
        ZonaCarga.getInstance().getPhaserLlegada().arriveAndAwaitAdvance();
        mutex.acquire();
        System.out.println("El barco " + b.getId() + " ha entrado en la zona carga.");
        if(listaBarcos.size()<5){
            listaBarcos.add(contadorLlegada, b); // Guardamos el depósito de gas al que va asociado el barco
        }else{
            listaBarcos.set(contadorLlegada,b);
        }
        contadorLlegada++;
        reiniciarContadorLlegada();
        mutex.release();
    }


    /**
     * Método que reposta el gas del contenedor de un petrolero.
     *
     * @param p
     * @throws InterruptedException
     */
    public void repostarGas(BarcoPetrolero p) throws InterruptedException, BrokenBarrierException {
        while (p.getDeposito_gas() < 3000) {
            if (contenedores_gas[listaBarcos.indexOf(p)] > 0) { // Mientras el depósito no esté vacio se rellena
                p.setDeposito_gas(p.getDeposito_gas() + 1000);
                System.out.println("Petrolero " + p.getId() + " repone GAS [" + p.getDeposito_gas() + "/3000]...");
                contenedores_gas[listaBarcos.indexOf(p)] -= 1000;
                System.out.println("Petrolero " + p.getId() + " ESPERA a reponder GAS...");
                repostar.await();
            }
        }
    }

    /**
     * Método que repone el gas de los depósitos de la plataforma.
     *
     * @throws InterruptedException
     */
    public void rellenarGas() throws InterruptedException {
        for (int i = 0; i < 5; i++) { // Rellena cada depósito y despierta el hilo del petrolero asociado.
           System.out.println("===RELLENANDO CONTENEDOR..." + i + "===");
            contenedores_gas[i] = 1000;
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
        b.setDeposito_agua(b.getDeposito_agua() + 1000);
        System.out.println("Petrolero " + b.getId() + " repone AGUA [" + b.getDeposito_agua() + "/5000]...");
        mutex.release();
    }

    /**
     * Metodo que reinicia el contador de los barcos de llegada a 5 para los próximos petroleros que lleguen
     */
    public void reiniciarContadorLlegada(){
        // Reiniciamos el contador de barcos para los próximos 5 petroleros.
        if (contadorLlegada == 5) {
            contadorLlegada = 0;
        }
    }

}