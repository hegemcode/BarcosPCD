import java.util.Random;

public class BarcoMercante extends Barco{

    private int contenedores[] = new int[3]; //Posición 0 = azucar; Posición 1 = sal; Posición 2 = harina.
    private Random rand = new Random();
    private Grua grua;

    /*
        Constructor parametrizado que define el barco mercante con su grua.
        @param id El id del barco.
        @param entrada Indica si el barco es de entrada o salida.
     */

    public BarcoMercante(int id, boolean entrada, boolean mercantil, boolean petrolero, int contAzucar, int contSal, int contHarina) {
        super(id, entrada,mercantil, petrolero);
        contenedores[0] = contAzucar;
        contenedores[1] = contSal;
        contenedores[2] = contHarina;
        grua = new Grua("mercante");
    }
    /*
        Una vez el barco mercantil entra al puerto, antes de salir, tiene que depositar en la plataforma todos sus contenedores de harina, sal y azucar.
     */
    @Override
    public void run() {
        super.run();
        int caso = 0;

            while(contenedores[0] > 0 || contenedores[1] > 0 || contenedores[2] > 0) {
                caso = rand.nextInt(3);
                switch (caso) {
                case 0:
                    if(contenedores[0] != 0) {
                        grua.put("sal",this);
                    }
                    break;
                case 1:
                    if(contenedores[1] != 0) {
                        grua.put("harina",this);
                    }
                    break;
                case 2:
                    if(contenedores[2] != 0) {
                        grua.put("azucar",this);
                    }
                    break;
                }
            }

        TorreControl.getInstance().permisoSalida(this);
        Puerta.getInstance().salir(this);
        TorreControl.getInstance().finSalida(this);
    }
    /*
    Método que reduce el numero de contenedores de un tipo especificado.
    @Param cont Que tipo de contenedor es: 0=azucar ; 1=sal ; 2=harina
     */
    public void reducirContenedor(int cont){
        this.contenedores[cont]--;
    }
    /*
    Método que devuelve el numero de contenedores totales.
    @return Numero de contenedores totales
     */
    public int numeroContenedores(){
        return this.contenedores[0] + this.contenedores[1] + this.contenedores[2]; }
}
