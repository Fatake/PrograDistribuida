/**
 * BUAP
 * Facultad en Ciencias de la Computacion
 * Ines Yaelin Rojas Huerta 201732130
 * Paulo Cesar Ruiz Lozano 201727952
 */
/**
 * Class Brazo
 */
public class Brazo implements Runnable {
  private static boolean[] in = { false, false }; 
  private static volatile int turn = -1; 
  /**
   * Capacidad inicial de carga en cada brazo
   */
  private int capacidad = 25;
  private Contenedor contenedor;
  private int id;
  
  //
  // Constructors
  //
  /**
   * 
   * @param id
   * @param capacidad
   * @param contenedor
   */
  public Brazo (int id, int capacidad, Contenedor contenedor) { 
    this.id = id;
    this.capacidad = capacidad;
    this.contenedor = contenedor;
  };
  
  //
  // Methods
  //
  @Override
  public void run() {
    for (int i = 0; i < capacidad; i++) {
      in[id] = true;// banderas quien está preparado
      turn = other(); // obtiene el turno
      int aux = 0;
      while (in[other()] && turn == other()) { 
        aux = contenedor.descargarUnaPieza();
      }
      if ( aux < 0) {
        break;
      }
      System.out.println("[Brazo "+id+"] Quitando pieza "+(i+1)+" de mis "+capacidad+" Quedan: "+aux+" piezas.");
      in[id] = false;
    }
      
  }

  private int other() { 
    return id == 0 ? 1 : 0; 
  }

  //
  // Accessor methods
  //
  /**
   * Set the value of capacidad
   * Capacidad inicial de carga en cada brazo
   * @param newVar the new value of capacidad
   */
  public void setCapacidad (int newVar) {
    capacidad = newVar;
  }

  /**
   * Get the value of capacidad
   * Capacidad inicial de carga en cada brazo
   * @return the value of capacidad
   */
  public int getCapacidad () {
    return capacidad;
  }

  /**
   * Set the value of contenedor
   * @param newVar the new value of contenedor
   */
  public void setContenedor (Contenedor newVar) {
    contenedor = newVar;
  }

  /**
   * Get the value of contenedor
   * @return the value of contenedor
   */
  public Contenedor getContenedor () {
    return contenedor;
  }

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (Integer newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public Integer getId () {
    return id;
  }
}
