
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;

public class FileImpl extends UnicastRemoteObject
  implements FileInterface {

   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private String name;

   /**
    * Constructor
    * @param s
    * @throws RemoteException
    */
   public FileImpl(String s) throws RemoteException{
      super();
      this.name = s;
   }

   //
   // Metodos
   //

   /**
    * Funcion lista archivos del servidor
    */
   public File[] listar() throws RemoteException{  
      File[] archivos  = null;
      try {
         File dir = new File(".");
         if(dir.exists()) {
            if(dir.isDirectory()) {
               archivos = dir.listFiles();
            }
         }
      } catch(Exception e){
         System.out.println("Error: "+e.getMessage());
         e.printStackTrace();
      }
      return archivos;
   }

   /**
    * Descarga el archivo dado
    */
   public byte[] downloadFile(String fileName) throws RemoteException{
      try {
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new
         BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         System.out.println("FileImpl: "+e.getMessage());
         e.printStackTrace();
         return(null);
      }
   }

   @Override
   public int cuentaLineas(String nombreArchivo) throws RemoteException {
      // TODO Auto-generated method stub
      return 0;
   }
   @Override
   public int cuentavocales(String nombreArchivo) throws RemoteException {
      // TODO Auto-generated method stub
      return 0;
   }
   @Override
   public void escribe(OutputStream os) throws RemoteException {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void imprimir() throws RemoteException {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void respaldar(String nombreArchivo) throws RemoteException {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void copiar(String nombreArchivoDestino) throws RemoteException {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void renombrar(String nombreArchivo) throws RemoteException {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void eliminar(String nombreArchivo) throws RemoteException {
      // TODO Auto-generated method stub
      
   }
}