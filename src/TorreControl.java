import javax.sql.rowset.BaseRowSet;

public class TorreControl {
    private int b_entrando, b_saliendo;

    public TorreControl() {
        this.b_entrando = 0;
        this.b_saliendo = 0;
    }

    public  boolean permisoEntrada(Barco b) {
        synchronized (this) {
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");
            System.out.println("El barco " + b.getId() + " pide permiso para entrar...");

            return this.b_saliendo == 0;
        }
    }

    public  boolean permisoSalida(Barco b) {
        synchronized (this) {
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
            System.out.println("El barco " + b.getId() + " pide permiso para salir...");
        }
        return this.b_entrando == 0;
    }

    public  void finEntrada(Barco b) {
        synchronized (this) {
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            System.out.println("El barco " + b.getId() + " ha acabado de entrar...");
            this.b_entrando--;
            notifyAll();

        }
    }

    public  void finSalida(Barco b) {
        synchronized (this) {
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            System.out.println("El barco " + b.getId() + " ha acabado de salir...");
            this.b_saliendo--;
            notifyAll();
        }
    }

    public synchronized void incEntrando () { this.b_entrando++; }
    public synchronized void incSaliendo () { this.b_saliendo++; }
}
