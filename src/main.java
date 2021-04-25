import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase principal del programa.
 * @author Jorge del Castillo Gómez, Pedro del Castillo Gómez, Miguel Jara Arroyo
 */
public class main {
    public static void main(String[] args) {

        // PLATAFORMA, ZONA DE CARGA Y GRUAS DE LA PLATAFORMA.
        Plataforma p = Plataforma.getInstance();
        ZonaCarga z = ZonaCarga.getInstance();
        // Las gruas siempre intentarán coger de la plataforma, por tanto cuando todos los barcos salen el programa no acaba.
        Thread g1 = new Thread(new Grua("sal"));
        Thread g2 = new Thread(new Grua("azucar"));
        Thread g3 = new Thread(new Grua("harina"));

        // BARCOS PETROLEROS Y BARCO MERCANTE.
        Thread t1 = new Thread(new BarcoPetrolero(1, true,false,true));
        Thread t2 = new Thread(new BarcoPetrolero(2, true,false,true));
        Thread t3 = new Thread(new BarcoPetrolero(3, true,false,true));
        Thread t4 = new Thread(new BarcoPetrolero(4, true,false,true));
        Thread t5 = new Thread(new BarcoPetrolero(5, true,false,true));
        Thread m1 = new Thread(new BarcoMercante(11,true,true,12,20,5, false ));

        // BARCOS NORMALES DE ENTRADA Y SALIDA
        Thread t6 = new Thread(new Barco(6, false,false,false));
        Thread t7 = new Thread(new Barco(7, true,false,false));
        Thread t8 = new Thread(new Barco(8, true,false,false));
        Thread t9 = new Thread(new Barco(9, false,false,false));
        Thread t10 = new Thread(new Barco(10, false,false,false));

        // RUN DE LOS THREADS
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        m1.start();
        g1.start();
        g2.start();
        g3.start();

        /*
         OPCIONA: 5 BARCOS PETROLEROS MÁS PARA PROBAR, EN CASO DE PROBARLOS, CAMBIAR EN LA CLASE REPONEDOR la "i" del while a 6.

        Thread t11 = new Thread(new BarcoPetrolero(12, true,false,true));
        Thread t12 = new Thread(new BarcoPetrolero(13, true,false,true));
        Thread t13 = new Thread(new BarcoPetrolero(14, true,false,true));
        Thread t14 = new Thread(new BarcoPetrolero(15, true,false,true));
        Thread t15 = new Thread(new BarcoPetrolero(16, true,false,true));
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        */
    }
}


