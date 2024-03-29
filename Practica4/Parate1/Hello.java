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
}
