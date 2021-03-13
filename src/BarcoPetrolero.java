/*
    Clase que representa los barcos petroleros.
    Atributos:
    - deposito_agua: el deposito de agua del barco
    - deposito_gas: el deposito de gas del barco
 */
public class BarcoPetrolero extends Barco{
    private int deposito_agua = 0;
    private int deposito_gas = 0;

/*
    Constructor parametrizado del petrolero. LLama al constructor parametrizado de la clase Barco.
    @param id Identificador del barco
    @param entrada Booleano que indica si es de entrada o salida
    @param mercantil Booleano que indica si es mercante
    @param petrolero Booleano que indica si es petrolero
 */
    public BarcoPetrolero(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        super(id, entrada, mercantil, petrolero);
    }
    /*
    Método run que se ejecuta al crear un hilo del petrolero.
     */
    @Override
    public void run() {
        super.run();
        try { ZonaCarga.getInstance().llegar(this); } catch (InterruptedException e) { }

        TorreControl.getInstance().permisoSalida(this);
        Puerta.getInstance().salir(this);
        TorreControl.getInstance().finSalida(this);
    }

    public void setDeposito_agua(int cantidad){ deposito_agua = cantidad; }
    public void setDeposito_gas(int cantidad){deposito_gas = cantidad;}
    public int getDeposito_agua(){return deposito_agua;}
    public int getDeposito_gas(){return deposito_gas;}
}
