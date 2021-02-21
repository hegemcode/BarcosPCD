/*
        Representa la puerta en la cual los barcos saldrán y entrarán dependiendo de su sentido.
        */

public class Puerta {
    private static Puerta puerta;
    private String id;

    /*
        Constructor por defecto privado de la puerta.
     */
    private Puerta() {
        this.id = "2530h";
    }

    /*
        Devuelve o crea la unica instancia puerta del programa.
     */
    public static Puerta getInstance() {
        if (puerta == null) {
            puerta = new Puerta();
        }
        return puerta;
    }

    /*
        Método de la puerta que indica cuando un barco b entra.
        @param b El barco que va a entrar por la puerta.
     */
     public synchronized void entrar(Barco b) {
        System.out.println("El barco " + b.getId() + " entra");
        System.out.println("El barco " + b.getId() + " entra");
        System.out.println("El barco " + b.getId() + " entra");
    }

    /*
        Método de la puerta que indica cuando un barco va a salir.
        @param b El barco que saldrá de la puerta
     */
     public  synchronized  void salir(Barco b) {
        System.out.println("El barco " + b.getId() + " sale");
        System.out.println("El barco " + b.getId() + " sale");
        System.out.println("El barco " + b.getId() + " sale");
    }
}