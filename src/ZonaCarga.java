import java.util.concurrent.*;
public class ZonaCarga {
    private static ZonaCarga z;
    private int contenedorAgua = 1000000;
    private int[] contenedores_gas = new int[5];
    Semaphore mutex,llegada,agua;
    int contador = 0;

    private ZonaCarga(){
        mutex = new Semaphore(1);
        agua = new Semaphore(1);
        llegada = new Semaphore(5);
        for(int i = 0; i < 5; i++){
            contenedores_gas[i] = 1000;
        }
    }

    public synchronized static ZonaCarga getInstance(){
        if(z == null){
            z = new ZonaCarga();
        }
        return z;
    }

    public void llegar(BarcoPetrolero b) throws InterruptedException {
        mutex.acquire();
        llegada.acquire();
        if(llegada.drainPermits() == 0){ // Si no hay mas capacidad para acceder al recurso, es decir, si han entrado los 5 barcos

        }
        else{
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
            System.out.println("Espera el barco " + b.getId() + " al resto de petroleros para repostar...");
        }

        mutex.release();
    }
    public void cogerAgua(BarcoPetrolero b){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.setDeposito_agua(5000);
        System.out.println("El barco petrolero " + b.getId() + " coge 5000l de agua...");
        System.out.println("El barco petrolero " + b.getId() + " coge 5000l de agua...");
        System.out.println("El barco petrolero " + b.getId() + " coge 5000l de agua...");
        mutex.release();
    }
}
