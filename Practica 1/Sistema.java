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
     * Donde el hilo[0] es productor
     * los otros 10 hilos son consumidores
     * @param args
     */
    public static void main(String[] args) {
      // Contenedor de piezas
      Contenedor contenedor = new Contenedor(0, 50);

      // Brazos
      Brazo brazo1 = new Brazo(1, 12, contenedor);
      Brazo brazo2 = new Brazo(2, 7, contenedor);

      // Arreglo de hilos
      Thread[] hilos = new Thread[2];


      hilos[0] = new Thread(brazo1);
      hilos[0].start();

      hilos[1] = new Thread(brazo2);
      hilos[1].start();



      for (int i = 0; i < hilos.length; i++) {
          try{
              hilos[i].join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
      System.out.println("[*] End Program");
  }
}
