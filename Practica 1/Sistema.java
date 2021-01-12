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
        Contenedor contenedor = new Contenedor(0, 50);

        // Brazos
        Brazo brazo1 = new Brazo(1, 30, contenedor);
        Brazo brazo2 = new Brazo(2, 30, contenedor);

        System.out.println("[*] Iniciando el contenedor con: "+contenedor.getPiezas()+" Piezas");
        System.out.println("[*] Iniciando Brazo 1 con: "+brazo1.getCapacidad()+" de Capacidad");
        System.out.println("[*] Iniciando Brazo 2 con: "+brazo2.getCapacidad()+" de Capacidad");

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
        if (contenedor.getPiezas() != 0) {
            System.out.println("[*]Brazos a maxima capacidad; El contenedor aun tiene:"+contenedor.getPiezas());
        }else{
            System.out.println("[*]Contenedor vacio");
        }
        System.out.println("[*] End Program");
    }
}
