
public class main {
    public static void main(String[] args) {
        /*

        Plataforma p = Plataforma.getInstance();
        Thread g1 = new Thread(new Grua("sal"));
        Thread g2 = new Thread(new Grua("azucar"));
        Thread g3 = new Thread(new Grua("harina"));
        g1.start();
        g2.start();
        g3.start(); */

        ZonaCarga zonaCarga = new ZonaCarga();
        Thread t1 = new Thread(new Petrolero(1, true,false, true, zonaCarga));
        Thread t2 = new Thread(new Petrolero(2, true,false, true, zonaCarga));
        Thread t3 = new Thread(new Petrolero(3, true,false, true, zonaCarga));
        Thread t4 = new Thread(new Petrolero(4, true,false, true, zonaCarga));
        Thread t5 = new Thread(new Petrolero(5, true,false, true, zonaCarga));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }
}


