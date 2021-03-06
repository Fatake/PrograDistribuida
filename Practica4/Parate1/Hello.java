import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Remote Class for the "Hello, world!" example.
 */
public class Hello extends UnicastRemoteObject implements HelloInterface {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String message;

  /**
   * Construct a remote object
   * 
   * @param msg the message of the remote object, such as "Hello, world!".
   * @exception RemoteException if the object handle cannot be constructed.
   */
  public Hello(String msg) throws RemoteException {
    message = msg;
  }

  /**
   * Implementation of the remotely invocable method.
   * 
   * @return the message of the remote object, such as "Hello, world!".
   * @exception RemoteException if the remote invocation fails.
   */
  public String say() throws RemoteException {
    return message;
  }

  @Override
  public int cuentaLineas(String nombrearchivo) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int cuentavocales(String nombrearchivo) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void escribe(OutputStream os) {
    // TODO Auto-generated method stub

  }

  @Override
  public void imprimir() {
    // TODO Auto-generated method stub

  }

  @Override
  public void respaldar(String nombrearchivo) {
    // TODO Auto-generated method stub

  }

  @Override
  public void copiar(String nombrearchivodestino) {
    // TODO Auto-generated method stub

  }

  @Override
  public void renombrar(String nombrearchivo) {
    // TODO Auto-generated method stub

  }

  @Override
  public void eliminar(String nombrearchivo) {
    // TODO Auto-generated method stub
      
  }
}
