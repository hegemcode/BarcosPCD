public class BarcoPetrolero extends Barco{
    private int deposito_agua = 0;
    private int deposito_gas = 0;

    public BarcoPetrolero(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        super(id, entrada, mercantil, petrolero);
    }
    public void setDeposito_agua(int cantidad){ deposito_agua = cantidad; }
}
