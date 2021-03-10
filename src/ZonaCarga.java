import java.util.concurrent.*;
public class ZonaCarga {
    private static ZonaCarga z;
    private int contenedorAgua;
    private int[] contenedores_gas = new int[5];
    private Semaphore[] llegada = new Semaphore[5];
    private Semaphore mutex;
    int contadorLlegada = 0;

    private ZonaCarga(){
        mutex = new Semaphore(1);
        this.contenedorAgua = 1000000;
        for(int i = 0; i < 5; i++){
            llegada[i] = new Semaphore(0);
        }
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
        // ZONA CRÍTICA
        // repostarGas
        repostarAgua(b);
    }

    public void repostarAgua(BarcoPetrolero b){
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
