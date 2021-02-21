
public class main {
    public static void main(String[] args) {
        Puerta puerta = new Puerta();
        TorreControl torre = new TorreControl();
        Thread t1 = new Thread(new Barco(1, true, puerta, torre));
        Thread t2 = new Thread(new Barco(2, false, puerta,  torre));
        Thread t3 = new Thread(new Barco(3, false, puerta,  torre));
        t1.start();
        t2.start();
        t3.start();
    }
}


