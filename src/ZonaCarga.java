import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.*;
public class ZonaCarga {
    private static ZonaCarga z;
    private int contenedorAgua;
    private int[] contenedores_gas = new int[5];
    private Semaphore[] llegada = new Semaphore[5];
    private Semaphore[] repostar = new Semaphore[5];
    private Semaphore[]  vacio = new Semaphore[5];
    private Semaphore mutex, mutex2;
    ArrayList <BarcoPetrolero> listaBarcos;
    int contadorLlegada = 0;
    private Thread reponedor = new Thread(new Reponedor());

    private ZonaCarga(){
        mutex = new Semaphore(1);
        mutex2 = new Semaphore(5);
        this.contenedorAgua = 1000000;
        listaBarcos = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            llegada[i] = new Semaphore(0);
            vacio[i] = new Semaphore(0);
            repostar[i] = new Semaphore(0);
        }
        for(int i = 0; i < 5; i++){
            contenedores_gas[i] = 1000;
        }
        reponedor.start();
    }

    public synchronized static ZonaCarga getInstance(){
        if(z == null){
            z = new ZonaCarga();
        }
        return z;
    }

    public void llegar(BarcoPetrolero b) throws InterruptedException {
        mutex2.acquire();
        mutex.acquire();
        listaBarcos.add(contadorLlegada, b);
        contadorLlegada++;
        if(contadorLlegada != 5){
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            mutex.release();
            llegada[contadorLlegada-1].acquire();
        }
        else{
            mutex.release();
            for(int i = 0; i < contadorLlegada - 1; i++){
                llegada[i].release();
            }
        }

        while (b.getDeposito_gas() < 3000) {
            repostarGas(b);
        }

        repostarAgua(b);
        if (contadorLlegada == 5) {
            contadorLlegada = 0;
        }
        mutex2.release();
    }

    public void repostarGas(BarcoPetrolero p) throws InterruptedException {
        if( contenedores_gas[listaBarcos.indexOf(p)] > 0) {
            p.setDeposito_gas(p.getDeposito_gas() + 1000);
            System.out.println("Petrolero " + p.getId() + " repone GAS ["+ p.getDeposito_gas() + "/3000]...");
            contenedores_gas[listaBarcos.indexOf(p)] -= 1000;
        }
        if (contenedores_gas[listaBarcos.indexOf(p)] == 0) {
            System.out.println("El barco petrolero " + p.getId() + " ESPERA PARA REPONER GAS...");
            repostar[listaBarcos.indexOf(p)].release();
            vacio[listaBarcos.indexOf(p)].acquire();
        }
    }

    public void rellenarGas() throws InterruptedException {
        for(int i = 0; i < 5; i++) {
            System.out.println("Esperando a que se vacien todos los depósitos");
            repostar[i].acquire();
        }
        for(int i = 0; i < 5; i++) {
            System.out.println("===RELLENANDO CONTENEDOR..." + i + "===");
            contenedores_gas[i] = 1000;
            vacio[i].release();
        }

    }
    public void repostarAgua(BarcoPetrolero b){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace(); }
        while(b.getDeposito_agua() < 5000){
            b.setDeposito_agua(b.getDeposito_agua()+1000);
            System.out.println("Petrolero " + b.getId() + " repone AGUA ["+ b.getDeposito_agua() + "/5000]...");
        }
        mutex.release();
    }
}