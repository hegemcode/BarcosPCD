
public class main {
    public static void main(String[] args) {
        /*
        Thread t1 = new Thread(new Barco(1, true,false));
        Thread t2 = new Thread(new Barco(2, false,false));
        Thread t3 = new Thread(new Barco(3, false,false));
        Thread m1 = new Thread(new BarcoMercante(4,true,true));
        Thread t4 = new Thread(new Barco(4, false));
        Thread t5 = new Thread(new Barco(5, true));
        Thread t6 = new Thread(new Barco(6, true));
        Thread t7 = new Thread(new Barco(7, false));
        */

        Plataforma p = Plataforma.getInstance();
        Thread m1 = new Thread(new BarcoMercante(4,true,true));
        m1.start();
        /*t1.start();
        t2.start();
        t3.start();
         */

        /*t4.start();
        t5.start();
        t6.start();
        t7.start();
         */
    }
}


