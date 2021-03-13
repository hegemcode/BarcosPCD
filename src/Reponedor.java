public class Reponedor implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                ZonaCarga.getInstance().rellenarGas();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
