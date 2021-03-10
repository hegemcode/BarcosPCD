/*
    Barco mercante que hereda de la clase estandar de barcos "Barco"
 */
import java.util.Random;
public class BarcoMercante extends Barco{

    private int sal_contenedores = 4;
    private int harina_contenedores = 5;
    private int azucar_contenedores = 2;
    private Random rand = new Random();
    private Grua grua;

    /*
        Constructor parametrizado que define el barco mercante con su grua.
        @param id El id del barco.
        @param entrada Indica si el barco es de entrada o salida.
     */
    public BarcoMercante(int id, boolean entrada, boolean mercantil, boolean petrolero) {
        super(id, entrada,mercantil, petrolero);
        grua = new Grua("mercante");
    }
    /*
        Una vez el barco mercantil entra al puerto, antes de salir, tiene que depositar en la plataforma todos sus contenedores de harina, sal y azucar.
     */
    @Override
    public void run() {
        super.run();
        int caso = 0;

            while(sal_contenedores > 0 || azucar_contenedores > 0 || harina_contenedores > 0) {

                caso = rand.nextInt(3);
                switch (caso) {
                case 0:
                    if(sal_contenedores != 0) {
                        grua.put("sal",this);
                    }
                    break;
                case 1:
                    if(harina_contenedores != 0) {
                        grua.put("harina",this);
                    }
                    break;
                case 2:
                    if(azucar_contenedores != 0) {
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
        Decrementa el numero de contenedores de sal del barco mercante en una unidad
     */
    public void reducirContenedorSal(){this.sal_contenedores--;}
    /*
        Decrementa el numero de contenedores de azucar del barco mercante en una unidad
     */
    public void reducirContenedorAzucar(){this.azucar_contenedores--;}
    /*
        Decrementa el numero de contenedores de harina del barco mercante en una unidad
     */
    public void reducirContenedorHarina(){this.harina_contenedores--;}
    /*
       Devuelve el numero total de contenedores del barco mercante en un momento dado.
       @return Total de contenedores del barco.
     */
    public int numeroContenedores(){ return this.harina_contenedores + this.sal_contenedores + this.sal_contenedores; }
}
