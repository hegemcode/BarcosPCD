public class Reponedor implements Runnable{
    @Override
    public void run() {
        int i = 0;
        try {
            while (i < 3)  {
                ZonaCarga.getInstance().rellenarGas();
                System.out.println("Incrementa la i");
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
