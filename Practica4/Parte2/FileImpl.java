
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
   private int numeroDeVocales(String frase) {
      int res = 0;
      String fraseMin = frase.toLowerCase();

      for (int i = 0; i < fraseMin.length(); ++i) {
          switch(fraseMin.charAt(i)) {
              case 'a':
              case 'e': 
              case 'i':
              case 'o':
              case 'u':
                  res++;
                  break;
              default:
            }
         }
      return res;
   }

   @Override
   public int cuentaLineas(String nombreArchivo) throws RemoteException { 
      BufferedReader reader;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
			String line = reader.readLine();
         
			while (line != null) {
				// read next line
				line = reader.readLine();
            contador ++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      return contador;
   }

   
   @Override
   public int cuentaVocales(String nombreArchivo) throws RemoteException {
      BufferedReader reader;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
			String line = reader.readLine();
         
			while (line != null) {
				// read next line
				line = reader.readLine();
            contador += numeroDeVocales(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      return contador;
   }

   @Override
   public void escribe(OutputStream os) throws RemoteException {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void imprimir() throws RemoteException {
      BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
			String line = reader.readLine();
         
			while (line != null) {
				// read next line
				line = reader.readLine();
            System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
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