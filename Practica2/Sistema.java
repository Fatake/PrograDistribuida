/**
 * BUAP
 * Facultad en Ciencias de la Computacion
 * Ines Yaelin Rojas Huerta 201732130
 * Paulo Cesar Ruiz Lozano 201727952
 */

/**
 * Class Sistema
 */
public class Sistema {
    //
    // Constructors
    //
    public Sistema () { };

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Contenedor de piezas
        Contenedor contenedores[] = {new Contenedor("A", 50),new Contenedor("B", 50)};

        // Brazos
        BrazoProduccion brazo1 = new BrazoProduccion(1, 32, contenedores);
        BrazoProduccion brazo2 = new BrazoProduccion(2, 7, contenedores);

        for (Contenedor cont : contenedores) {
            System.out.println("[*] Iniciando el contenedor "+cont.getId()+" con: "+cont.getPiezas()+" Piezas.");
        }

        System.out.println("[*] Iniciando Brazo 1 con: "+brazo1.getCapacidad()+" de Capacidad.");
        System.out.println("[*] Iniciando Brazo 2 con: "+brazo2.getCapacidad()+" de Capacidad.");

        System.exit(1);
        // Arreglo de hilos
        Thread[] hilos = new Thread[2];

        hilos[0] = new Thread(brazo1);
        hilos[0].start();

        hilos[1] = new Thread(brazo2);
        hilos[1].start();
        
        // En espera a terminar los procesos
        for (int i = 0; i < hilos.length; i++) {
            try{
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        if (contenedor.getPiezas() > 0) {
            System.out.println("[*] Brazos a maxima capacidad.");
            System.out.println("[*] El contenedor aun tiene: "+contenedor.getPiezas()+" Piezas.");
        }else{
            System.out.println("[*] Contenedor vacio.");
        }*/
        System.out.println("[*] End Program.");
    }
}
