import java.io.File;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;

/**
 * Remote Class for the "Hello, world!" example.
 */
public class IOArchivo extends UnicastRemoteObject implements IOArchivoInterface {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String nombreArchivo;
  private File archivo;

  /**
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  public IOArchivo(String nombreArchivo) throws RemoteException {
    this.nombreArchivo = nombreArchivo;
    File archivo = new File(this.nombreArchivo);

    // Si no existe
    if (!archivo.exists()) {
      System.out.println("[!] Error no existe el archivo\n");
      System.exit(1);
    }
  }

  /**
   * Funcion que dice si el objeto fue invocado correctamente
   * @return
   * @throws RemoteException
   */
  public String conectado() throws RemoteException{
    return "ObjetoExitoso!";
  }

  /**
   * 1
   * Ines
   * Devuelve el numero de lineas del archivo;
   * 
   * @param nombreArchivo
   * @return
   * @throws RemoteException
   */
  int cuentaLineas (String nombreArchivo) throws RemoteException{
    return 0;
  }

  /**
   * 2
   * Ines
   * Devuelve el numero de vocales en el archivo
   * 
   * @param nombreArchivo
   * @return
   * @throws RemoteException
   */
  int cuentavocales (String nombreArchivo) throws RemoteException{
    return 0;
  }

  /**
   * 3
   * Ines
   * Escribe en el contenido del archivo a os
   * 
   * @param os
   * @throws RemoteException
   */
  void escribe (OutputStream os) throws RemoteException{
    
  }

  /**
   * 4
   * Paulo
   * Imprime el contenido del archivo a pantalla
   * 
   * @throws RemoteException
   */
  void imprimir () throws RemoteException,IOException{
    FileReader  fr = new FileReader (this.archivo);
    BufferedReader  br = new BufferedReader(fr);
    String linea;
    while((linea=br.readLine())!=null){
      System.out.println(linea);
    }
  }

  /**
   * 5
   * Paulo
   * Crea Una copia el archivo;
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void respaldar(String nombreArchivo) throws RemoteException{

    File respaldo = new File(nombreArchivo);
    if (!respaldo.exists()) {
      respaldo.createNewFile();
    }

    if (this.archivo.exists()) {
      try {
        InputStream in = new FileInputStream(this.archivo);
        OutputStream out = new FileOutputStream(respaldo);
        // We use a buffer for the copy (Usamos un buffer para la copia).
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        System.out.println("[i] Respaldo Existoso");
      } catch (IOException ioe) {
        ioe.printStackTrace();
        System.out.println("[!] Error al Respaldar");
      }
    }else{
      System.out.println("[!] No existe el archivo en el servidor");
    }
  }

  /**
   * 6
   * Paulo
   * copie el contenido de un archivo fuente a 
   * un archivo destino nombreArchivodestino
   * 
   * @param nombreArchivodestino
   * @throws RemoteException
   */
  void copiar (String nombreArchivodestino) throws RemoteException{
    File respaldo = new File(nombreArchivo);
    if (!respaldo.exists()) {
      respaldo.createNewFile();
    }

    if (this.archivo.exists()) {
      try {
        InputStream in = new FileInputStream(this.archivo);
        OutputStream out = new FileOutputStream(respaldo);
        // We use a buffer for the copy (Usamos un buffer para la copia).
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        System.out.println("[i] Copia Creada Existoso");
      } catch (IOException ioe) {
        ioe.printStackTrace();
        System.out.println("[!] Error al crear copia");
      }
    }else{
      System.out.println("[!] No existe el archivo en el servidor");
    }
  }
  
  /**
   * 7
   * Paulo
   * Renombrar el archivo
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void renombrar(String nombreArchivo) throws RemoteException{

  }
  
  /**
   * 8
   * Ines
   * Elimina el archivo de almacenamiento masivo.
   * 
   * @param nombreArchivo
   * @throws RemoteException
   */
  void eliminar(String nombreArchivo) throws RemoteException{

  }

}
