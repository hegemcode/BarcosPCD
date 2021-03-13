
// Clase que representa el reponedor de los depositos de gas y agua de la zona de carga.

public class Reponedor implements Runnable {
    @Override
    // Método run que se ejecuta al crear un hilo del petrolero.
    public void run() {
        int i = 0;
        try {
            while (i < 3) {
                ZonaCarga.getInstance().rellenarGas();
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
