import javax.sql.rowset.BaseRowSet;

public class TorreControl {
    private int b_entrando, b_saliendo;

    public TorreControl() {
        this.b_entrando = 0;
        this.b_saliendo = 0;
    }

    public synchronized void permisoEntrada(Barco b) {
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");
            while (b_saliendo != 0) {
                try {
                    System.out.println("ESPERANDO");
                    wait();
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
            System.out.println("ACCESO PERMITIDO");
            b_entrando++;
    }

    public synchronized void permisoSalida (Barco b) {
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
            while (b_entrando != 0) {
                try {
                    System.out.println("ESPERANDO");
                    wait();
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        System.out.println("ACCESO PERMITIDO");
        b_saliendo++;
    }

    public synchronized void finEntrada(Barco b) {
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            this.b_entrando--;
            notifyAll();
    }

    public synchronized void finSalida(Barco b) {
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            this.b_saliendo--;
            notifyAll();
    }
}
