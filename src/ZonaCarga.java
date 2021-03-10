import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ZonaCarga {
    private int Deposito_Agua;
    private int Deposito_Gas[];
    private List<Petrolero> pList; // Almacena los barcos que han llegado y que pueden respostar
    Semaphore mutex_1, mutex_2, agua, llegada;


    public ZonaCarga() {
        // Inicialmente los depositos de agua y gas estan llenos.
        this.Deposito_Agua = 100000;
        this.Deposito_Gas = new int[5];
        for (int i = 0; i < Deposito_Gas.length; i++) {
            Deposito_Gas[i] = 1000;
        }
        pList = new ArrayList<>();
        mutex_1 = new Semaphore(1);
        mutex_2 = new Semaphore(1);
        llegada = new Semaphore(5);
        agua = new Semaphore(1);

    }


    public void llegar(Petrolero p) throws InterruptedException {
        mutex_1.acquire();
        llegada.acquire();
        pList.add(p);
        // Si ya han llegado  los 5 petroleros
        if (pList.size() == 5) {
            for (Petrolero _p : pList) {
                System.out.println("El petrolero " + _p.getId() + " empieza a repostar...");
                System.out.println("El petrolero " + _p.getId() + " empieza a repostar...");
                System.out.println("El petrolero " + _p.getId() + " empieza a repostar...");
                //repostarGas(_p);
                repostarAgua(_p);
                llegada.release();
            }
        } else {
            System.out.println("El petrolero " + p.getId() + " espera para repostar...");
            System.out.println("El petrolero " + p.getId() + " espera para repostar...");
            System.out.println("El petrolero " + p.getId() + " espera para repostar...");
        }
        mutex_1.release();
    }

    public void repostarGas(Petrolero p) {

    }

    public void repostarAgua(Petrolero p) throws InterruptedException {
        // Mientras el contenedor de agua aun no este lleno
        while (p.getCont_agua() < 5000) {
            mutex_2.acquire();
            agua.acquire();
            System.out.println("El petrolero " + p.getId() + " reposta AGUA...");
            p.setCont_agua(p.getCont_agua() + 1000); // Seccion critica: Llenamos el contedor de agua
            agua.release();
            mutex_2.release();
        }
    }
}

