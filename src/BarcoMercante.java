import java.util.Random;

/**
 * Clase que representa los barcos mercantes que heredan de la clase Barco
 */
public class BarcoMercante extends Barco {

    private int contenedores[] = new int[3]; // Posición 0 = azucar; Posición 1 = sal; Posición 2 = harina.
    private Random rand = new Random();

    /**
     * Constructor parametrizado que define el barco mercante con su grua.
     *
     * @param id
     * @param entrada
     * @param mercantil
     * @param contAzucar
     * @param contSal
     * @param contHarina
     * @param petrolero
     */
    public BarcoMercante(int id, boolean entrada, boolean mercantil, int contAzucar, int contSal, int contHarina, boolean petrolero) {
        super(id, entrada, mercantil, petrolero);
        contenedores[0] = contAzucar;
        contenedores[1] = contSal;
        contenedores[2] = contHarina;
    }

    /**
     * Método que comienza al arrancar el hilo
     */
    @Override
    public void run() {
        super.run();
        int caso = 0;
        while (contenedores[0] > 0 || contenedores[1] > 0 || contenedores[2] > 0) {
            caso = rand.nextInt(3);
            switch (caso) {
                case 0:
                    if (contenedores[0] != 0) {
                        try {
                            Plataforma.getInstance().put("azucar", this);
                            contenedores[0]--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    if (contenedores[1] != 0) {
                        try {
                            Plataforma.getInstance().put("sal", this);
                            contenedores[1]--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    if (contenedores[2] != 0) {
                        try {
                            Plataforma.getInstance().put("harina", this);
                            contenedores[2]--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }

        // Una vez el mercante ha descargado su contenido, procede a salir del puerto
        TorreControl.getInstance().permisoSalida(this);
        Puerta.getInstance().salir(this);
        TorreControl.getInstance().finSalida(this);
    }

    /**
     * Método que reduce el numero de contenedores de un tipo especificado.
     *
     * @param cont Que tipo de contenedor es: 0=azucar ; 1=sal ; 2=harina
     */
    public void reducirContenedor(int cont) {
        this.contenedores[cont]--;
    }

    /**
     * Método que devuelve el numero de contenedores totales.
     *
     * @return Numero de contenedores totales
     */
    public int numeroContenedores() {
        return this.contenedores[0] + this.contenedores[1] + this.contenedores[2];
    }
}
