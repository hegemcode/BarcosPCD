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

    public int getCont_gas() {
        return cont_gas;
    }

    public void setCont_gas(int cont_gas) {
        this.cont_gas = cont_gas;
    }

    public int getCont_agua() {
        return cont_agua;
    }

    public void setCont_agua(int cont_agua) {
        this.cont_agua = cont_agua;
    }
}
