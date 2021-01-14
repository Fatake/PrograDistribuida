/**
 * BUAP
 * Facultad en Ciencias de la Computacion
 * Ines Yaelin Rojas Huerta 201732130
 * Paulo Cesar Ruiz Lozano 201727952
 */

/**
 * Class Contenedor
 */
public class Contenedor {
  public volatile boolean[] banderas = { false, false }; // estoy listo
  public volatile int turn = -1; // turno actual
  //
  // Fields
  //
  public int piezas;
  private int id;
  
  //
  // Constructors
  //
  public Contenedor (int id, int piezas) { 
    this.id = id;
    this.piezas = piezas;
  };
  
  /**
   * Metodo Descargar Pieza
   * @return
   */
  public synchronized int descargarUnaPieza(){
    this.piezas --;
    return this.piezas;
  }

  //
  // Accessor methods
  //
  /**
   * Set the value of piezas
   * @param newVar the new value of piezas
   */
  public void setPiezas (Integer newVar) {
    piezas = newVar;
  }

  /**
   * Get the value of piezas
   * @return the value of piezas
   */
  public int getPiezas () {
    return piezas;
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
