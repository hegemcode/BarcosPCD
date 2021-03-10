/*
Clase que representan a las gruas encargadas de mover contenedores de un tipo específico.
 */
public class Grua implements Runnable{
    private String id;

    /*
        Constructor parametrizado dado el id de la grua ("mercante" o "azucar" o "harina" o "sal").
        @param id El id de la grua.
     */
    public Grua(String id){
        this.id = id;
    }
    @Override
    public void run() {
            Plataforma.getInstance().get(id);
    }
    public void put(String contenedor, BarcoMercante b){
        Plataforma.getInstance().put(contenedor,b);
    }
}
