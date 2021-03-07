
public class main {
    public static void main(String[] args) {
        Plataforma p = Plataforma.getInstance();
        Thread g1 = new Thread(new Grua("sal"));
        Thread g2 = new Thread(new Grua("azucar"));
        Thread g3 = new Thread(new Grua("harina"));
        g1.start();
        g2.start();
        g3.start();

        Thread t1 = new Thread(new Barco(1, false,false));
        Thread t2 = new Thread(new Barco(2, true,false));
        Thread t3 = new Thread(new Barco(3, false,false));
        Thread t4 = new Thread(new Barco(4, true,false));
        Thread t5 = new Thread(new Barco(5, true,false));
        Thread m1 = new Thread(new BarcoMercante(8,true,true));
        Thread t6 = new Thread(new Barco(6, true,false));
        Thread t7 = new Thread(new Barco(7, false,false));
        m1.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

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


