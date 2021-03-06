import java.io.OutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote Interface for the "Hello, world!" example.
 */

public interface IOArchivoInterface extends Remote {
  /**
   * Funcion que dice si el objeto fue invocado correctamente
   * @return
   * @throws RemoteException
   */
  public String conectado() throws RemoteException;

  /**
   * 1
   * Ines
   * Devuelve el numero de lineas del archivo;
   * 
   * @param nombreArchivo
   * @return
   * @throws RemoteException
   */
  int cuentaLineas (String nombreArchivo) throws RemoteException; 

  /**
   * 2
   * Ines
   * Devuelve el numero de vocales en el archivo
   * 
   * @param nombreArchivo
   * @return
   * @throws RemoteException
   */
  int cuentavocales (String nombreArchivo) throws RemoteException; 

  /**
   * 3
   * Ines
   * Escribe en el contenido del archivo a os
   * 
   * @param os
   * @throws RemoteException
   */
  void escribe (OutputStream os) throws RemoteException;

  /**
   * 4
   * Paulo
   * Imprime el contenido del archivo a pantalla
   * 
   * @throws RemoteException
   */
  void imprimir () throws RemoteException; 

  /**
   * 5
   * Paulo
   * Crea Una copia el archivo;
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void respaldar(String nombreArchivo) throws RemoteException; 

  /**
   * 6
   * Paulo
   * copie el contenido de un archivo fuente a 
   * un archivo destino nombreArchivodestino
   * 
   * @param nombreArchivodestino
   * @throws RemoteException
   */
  void copiar (String nombreArchivodestino) throws RemoteException;
  
  /**
   * 7
   * Paulo
   * Renombrar el archivo
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void renombrar(String nombreArchivo) throws RemoteException;
  
  /**
   * 8
   * Ines
   * Elimina el archivo de almacenamiento masivo.
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void eliminar(String nombreArchivo) throws RemoteException; 
}
