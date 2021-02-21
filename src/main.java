
public class main {
    public static void main(String[] args) {
        Puerta.getInstance();
        Thread t1 = new Thread(new Barco(1, true));
        Thread t2 = new Thread(new Barco(2, false));
        Thread t3 = new Thread(new Barco(3, false));
        t1.start();
        t2.start();
        t3.start();
    }
}


