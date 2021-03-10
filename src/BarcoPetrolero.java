public class BarcoPetrolero extends Barco{
    private int deposito_agua = 0;
    private int deposito_gas = 0;


    public BarcoPetrolero(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        super(id, entrada, mercantil, petrolero);
    }

    @Override
    public void run() {
        super.run();
        try { ZonaCarga.getInstance().llegar(this); } catch (InterruptedException e) { }
    }

    public void setDeposito_agua(int cantidad){ deposito_agua = cantidad; }
    public void setDeposito_gas(int cantidad){deposito_gas = cantidad;}
    public int getDeposito_agua(){return deposito_agua;}
    public int getDeposito_gas(){return deposito_gas;}
}
