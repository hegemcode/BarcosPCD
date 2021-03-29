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
        //g1.start();
        //g2.start();
        //g3.start();



        // BARCOS PETROLEROS Y BARCO MERCANTE.
        Thread t1 = new Thread(new BarcoPetrolero(1, true,false,true));
        Thread t2 = new Thread(new BarcoPetrolero(2, true,false,true));
        Thread t3 = new Thread(new BarcoPetrolero(3, true,false,true));
        Thread t4 = new Thread(new BarcoPetrolero(4, true,false,true));
        Thread t5 = new Thread(new BarcoPetrolero(5, true,false,true));

        Thread t6 = new Thread(new BarcoPetrolero(6, true, false, true));
        Thread t7 = new Thread(new BarcoPetrolero(7, true, false, true));
        Thread t8 = new Thread(new BarcoPetrolero(8, true, false, true));
        Thread t9 = new Thread(new BarcoPetrolero(9, true, false, true));
        Thread t10 = new Thread(new BarcoPetrolero(10, true, false, true));
        //Thread m1 = new Thread(new BarcoMercante(11,true,true,12,20,5, false ));

        // BARCOS NORMALES DE ENTRADA Y SALIDA
        /*
        Thread t6 = new Thread(new Barco(6, false,false,false));
        Thread t7 = new Thread(new Barco(7, true,false,false));
        Thread t8 = new Thread(new Barco(8, true,false,false));
        Thread t9 = new Thread(new Barco(9, false,false,false));
        Thread t10 = new Thread(new Barco(10, false,false,false));
         */

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
        //m1.start();

    }
}