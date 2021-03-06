import java.io.OutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote Interface for the "Hello, world!" example.
 */

public interface HelloInterface extends Remote {
  /**
   * Remotely invocable method.
   * @return the message of the remote object, such as "Hello, world!".
   * @exception RemoteException if the remote invocation fails.
   */
  public String say() throws RemoteException;
  int cuentaLineas (String nombrearchivo); // que devuelva el número de líneas del archivo;
  int cuentavocales (String nombrearchivo); // que devuelva el número de líneas del archivo;
  void escribe (OutputStream os); // que escriba el contenido del archivo a os;
  void imprimir (); // que imprima el contenido del archivo a pantalla;
  void respaldar(String nombrearchivo); // que respalde el archivo;
  void copiar (String nombrearchivodestino); //; // que copie el contenido de un archivo fuente a un archivo destino nombrearchivodestino; 
  void renombrar(String nombrearchivo); // que renombre el archivo
  void eliminar(String nombrearchivo); // que elimine el archivo de almacenamiento masivo.
}
