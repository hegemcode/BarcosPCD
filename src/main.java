
public class main {
    public static void main(String[] args) {
        Puerta puerta = new Puerta();
        TorreControl torre = new TorreControl();
        Thread t1 = new Thread(new Barco(1, true, puerta, torre));
        Thread t2 = new Thread(new Barco(2, false, puerta,  torre));
        Thread t3 = new Thread(new Barco(3, false, puerta,  torre));
        Thread t4 = new Thread(new Barco(4, false, puerta,  torre));
        Thread t5 = new Thread(new Barco(5, true, puerta,  torre));
        Thread t6 = new Thread(new Barco(6, true, puerta,  torre));
        Thread t7 = new Thread(new Barco(7, false, puerta,  torre));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }
}


