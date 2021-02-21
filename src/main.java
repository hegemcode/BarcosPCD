
public class main {
    public static void main(String[] args) {
        Puerta puerta = new Puerta();
        Thread t1 = new Thread(new Barco(1, true, puerta));
        Thread t2 = new Thread(new Barco(2, false, puerta));
        Thread t3 = new Thread(new Barco(3, false, puerta));
        t1.start();
        t2.start();
        t3.start();
    }
}


