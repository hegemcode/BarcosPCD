public class BarcoMercante extends Barco{

    private int sal_contenedores = 2;
    private int harina_contenedores = 2;
    private int azucar_contenedores = 2;
    private Grua grua;
    /*
        Constructor parametrizado que define el barco mercante con su grua.
        @param id El id del barco.
        @param entrada Indica si el barco es de entrada o salida.
     */
    public BarcoMercante(int id, boolean entrada, boolean mercantil) {
        super(id, entrada,mercantil);
        grua = new Grua("mercante");
    }
    /*
        Una vez el barco mercantil entra al puerto, antes de salir, tiene que depositar en la plataforma todos sus contenedores de harina, sal y azucar.
     */
    @Override
    public void run() {
        super.run();
        // bucle
        // elección de un contenedor del barco
        // depositar en plataforma
        // salida del barco
        for(int i = 0; i < 2;i++){
            grua.put("sal",this);
            grua.put("azucar",this);
            grua.put("harina",this);
        }
    }
    public void reducirContenedorSal(){this.sal_contenedores--;}
    public void reducirContenedorAzucar(){this.azucar_contenedores--;}
    public void reducirContenedorHarina(){this.harina_contenedores--;}

}
