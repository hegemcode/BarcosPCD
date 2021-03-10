public class Petrolero extends Barco{
    private int cont_gas;
    private int cont_agua;
    private ZonaCarga zonaCarga;

    public Petrolero(int id, boolean entrada, boolean mercantil, boolean petrolero, ZonaCarga zonaCarga) {
        super(id, entrada, mercantil, petrolero);
        this.cont_agua = 0;
        this.cont_gas = 0;
        this.zonaCarga = zonaCarga;
    }

    @Override
    public void run () {
        super.run();
        try {
            zonaCarga.llegar(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
