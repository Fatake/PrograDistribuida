/**
 * BUAP
 * Facultad en Ciencias de la Computacion
 * Ines Yaelin Rojas Huerta 201732130
 * Paulo Cesar Ruiz Lozano 201727952
 */
/**
 * Class Brazo
 */
public class BrazoProduccion implements Runnable {
  private int capacidad;
  private Contenedor[] contenedor;
  private int id;
  private int productos = 0;
  
  /**
   * Contructor
   * @param id
   * @param capacidad
   * @param contenedor
   */
  public BrazoProduccion (int id, int capacidad, Contenedor[] contenedor) { 
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
      boolean pieza1 = false;
      boolean pieza2 = false;

      if (this.id == 1) { // Brazo 1
        pieza1 = contenedor[0].descargarUnaPieza();
        System.out.println("Brazo "+id+": Descargando una pieza del Contenedor A");
        pieza2 = contenedor[1].descargarUnaPieza();
        System.out.println("Brazo "+id+": Descargando una pieza del Contenedor B");
      }else if(this.id == 2){ // Brazo 2
        pieza1 = contenedor[1].descargarUnaPieza();
        System.out.println("Brazo "+id+": Descargando una pieza del Contenedor B");
        pieza2 = contenedor[0].descargarUnaPieza();
        System.out.println("Brazo "+id+": Descargando una pieza del Contenedor A");
      }
      
      if ( pieza1 && pieza2) {
        System.out.println("Brazo "+id+": Montando Producto "+(productos+1)+" de "+capacidad);
        productos ++;
      }else{
        if (pieza2 && pieza2 == false) {
          System.out.println("Brazo "+id+": No puedo hacer mas piezas Contenedor Vacio, saliendo ");
        }else{
          System.out.println("Brazo "+id+": No puedo hacer mas piezas  Contenedor Vacio, saliendo ");
        }
        break;
      }
    }
  }

  /**
   * Guarda la salida de cantidad de procutos
   */
  public void salida() {
    System.out.println("[+] Brazo "+id+": Hice "+productos+" productos. Termine.");
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

  public int getProductos(){
    return this.productos;
  }
}
