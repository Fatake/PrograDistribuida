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
  //
  // Fields
  //
  public int piezas;
  private String id;
  
  //
  // Constructors
  //
  public Contenedor (String id, int piezas) { 
    this.id = id;
    this.piezas = piezas;
  };
  
  /**
   * Metodo Descargar Pieza
   * @return
   */
  public synchronized boolean descargarUnaPieza(){
    if (this.piezas == 0) {
      return false;
    }
    this.piezas --;
    return true;
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
  public void setId (String newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public String getId () {
    return id;
  }

  
}
