/*
        Representa la puerta en la cual los barcos saldrán y entrarán dependiendo de su sentido.
        */

public class Puerta {
    private static Puerta puerta;
    private String id;

    /*
        Constructor de la puerta.
     */
    public Puerta() {
        this.id = "2530h";
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
    public synchronized void salir(Barco b) {
        System.out.println("El barco " + b.getId() + " sale");
        System.out.println("El barco " + b.getId() + " sale");
        System.out.println("El barco " + b.getId() + " sale");
    }
}