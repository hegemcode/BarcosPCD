
public class main {
    public static void main(String[] args) {
        Puerta.getInstance();
        TorreControl torre = new TorreControl();
        Thread t1 = new Thread(new Barco(1, true, torre));
        Thread t2 = new Thread(new Barco(2, false, torre));
        Thread t3 = new Thread(new Barco(3, false, torre));
        t1.start();
        t2.start();
        t3.start();
    }
}


