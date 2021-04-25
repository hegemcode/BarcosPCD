import pcd.util.Ventana;

public class EstadoPuertoImpl implements EstadoPuerto{
    private TorreControl torreControl;
    private Ventana v;

    public EstadoPuertoImpl(TorreControl torreControl,Ventana v) {
        this.torreControl = torreControl;
        this.v = v;
    }

    @Override
    public int consultarInfo() {
        System.out.println("Jefe de puerto accediendo al contador...");
        return TorreControl.getInstance().getContadorRemoto();
    }
}
