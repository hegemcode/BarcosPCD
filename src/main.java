/**
 * Clase principal del programa.
 *
 * @author Jorge del Castillo Gómez, Pedro del Castillo Gómez, Miguel Jara Arroyo
 */
public class main {
    public static void main(String[] args) {
        ZonaCarga z = ZonaCarga.getInstance();

        /*
        Plataforma p = Plataforma.getInstance();
        Thread g1 = new Thread(new Grua("sal"));
        Thread g2 = new Thread(new Grua("azucar"));
        Thread g3 = new Thread(new Grua("harina"));
        g1.start();
        g2.start();
        g3.start();
        */


        Thread t1 = new Thread(new BarcoPetrolero(1, true, false, true));
        Thread t2 = new Thread(new BarcoPetrolero(2, true, false, true));
        Thread t3 = new Thread(new BarcoPetrolero(3, true, false, true));
        Thread t4 = new Thread(new BarcoPetrolero(4, true, false, true));
        Thread t5 = new Thread(new BarcoPetrolero(5, true, false, true));
/*
        Thread t6 = new Thread(new BarcoPetrolero(6, true, false, true));
        Thread t7 = new Thread(new BarcoPetrolero(7, true, false, true));
        Thread t8 = new Thread(new BarcoPetrolero(8, true, false, true));
        Thread t9 = new Thread(new BarcoPetrolero(9, true, false, true));
        Thread t10 = new Thread(new BarcoPetrolero(10, true, false, true));
        //Thread m1 = new Thread(new BarcoMercante(8,true,true,3,2,4,false));
        // m1.start();*/


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        /*
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();*/


        /*
        Thread t4 = new Thread(new Barco(4, false));
        Thread t5 = new Thread(new Barco(5, true));
        Thread t6 = new Thread(new Barco(6, true));
        Thread t7 = new Thread(new Barco(7, false));
        */

        /*t4.start();
        t5.start();
        t6.start();
        t7.start();
         */
    }
}


