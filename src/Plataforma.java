import java.sql.SQLOutput;
import java.util.concurrent.locks.*;
/*
Clase que representa la plataforma donde los barcosMercantes depositan sus contenedores y las gruas los recogen.
 */
public class Plataforma {
    private static Plataforma p;
    private int capacidad = 1;
    private boolean barcoSinContenedores;

    Lock monitor = new ReentrantLock();
    Condition sal_grua = monitor.newCondition();
    Condition azucar_grua = monitor.newCondition();
    Condition harina_grua = monitor.newCondition();
    Condition espera_barco = monitor.newCondition();
    /*
        Constructor por defecto de la única plataforma que existe en el puerto. Actua de monitor para gestionar
        el depósito y la extracción de los contenedores entre el barco mercante y las gruas del puerto.
     */
    private  Plataforma(){
        this.barcoSinContenedores = false;
    }
    /*
        GetInstace de la plataforma (Singleton). Si la plataforma no está instanciada, la instancia llamando al constructor privado
        y la devuelve. En caso de que esté instanciada, la devuelve.
        @return La única plataforma instanciada.
     */
    public synchronized static Plataforma getInstance(){
        if(p == null){
            p = new Plataforma();
        }
        return p;
    }

    /*
       Método en el que un barco mercante pone un contenedor en la plataforma.
       @param contenedor String con el tipo de contenedor.
       @param b BarcoMercante encargado de poner el contenedor.
     */
    public void put(String contenedor, BarcoMercante b){
        monitor.lock();
        try{
            if(capacidad == 0){
                try {
                    espera_barco.await();
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            capacidad--;
            System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            System.out.println("Contenedor " + contenedor + " depositado en la plataforma...");
            switch(contenedor){
                case "sal": sal_grua.signal();
                b.reducirContenedor(1);
                break;
                case "azucar": azucar_grua.signal();
                b.reducirContenedor(0);
                break;
                case "harina": harina_grua.signal();
                b.reducirContenedor(2);
                break;
            }
            if(b.numeroContenedores() == 0){
                barcoSinContenedores = true;
                sal_grua.signal();
                harina_grua.signal();
                azucar_grua.signal();
            }
        }
        finally{monitor.unlock();}
    }

    /*
    Método en el que una grua extrae el contenedor de la plataforma.
    @param contenedor Tipo de contenedor que se extrae de la plataforma.
     */
    public void get(String contenedor){
        monitor.lock();
        try {
            while (capacidad == 1 && !barcoSinContenedores) {
                try {
                    switch (contenedor) {
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
                } catch (InterruptedException e) { e.printStackTrace(); }
                if(!barcoSinContenedores) {
                    capacidad++;
                    System.out.println("Contenedor " + contenedor + " extraido de la plataforma...");
                    System.out.println("Contenedor " + contenedor + " extraido de la plataforma...");
                    System.out.println("Contenedor " + contenedor + " extraido de la plataforma...");
                    espera_barco.signal();
                }
            }
        } finally{monitor.unlock();}
    }
}
