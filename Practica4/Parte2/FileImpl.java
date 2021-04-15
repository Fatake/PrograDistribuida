import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FileImpl extends UnicastRemoteObject implements FileInterface {
   //
   // Atributos
   //
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
      BufferedReader reader;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
			String line = reader.readLine();
         
			while (line != null) {
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
      BufferedReader reader = null;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
   
			String line = "";
         
			do {
				// read next line
				line = reader.readLine();
            if(line == null){
               break;
            }
            System.out.println(contador);
            System.out.print(line);
            int res = 0;
            String fraseMin = line.toLowerCase();
            for (int i = 0; i < fraseMin.length(); ++i) {
               char aux = fraseMin.charAt(i);
               if("aeiou".contains(String.valueOf(aux).toLowerCase())){
                  res ++;
               }
            }
            contador += res;
			}while (line != null);
         return contador;	
		} catch (IOException e) {
			e.printStackTrace();
		}
      finally{
         try {
            reader.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      } 
      return contador;
   }

   @Override
   public Boolean escribe(String destino, String mensaje) throws RemoteException {
		File fDestino = new File(destino);
		try {
		   OutputStream os = new FileOutputStream(fDestino,true);	
		   os.write(mensaje.getBytes(),0,mensaje.length());
		   os.close();
      
		} catch (IOException ioe){
		   ioe.printStackTrace();
         return false;
		}
      return true;
   }

   @Override
   public String[] imprimir(String archivo) throws RemoteException {
      BufferedReader reader;
      File Farchivo = new File(archivo);
      List <String> temporal = new  ArrayList <String>();
      String[] lineas = null;
		try {
			reader = new BufferedReader(new FileReader(Farchivo));
			String line = null;
         
			do{
				line = reader.readLine();
            if (line == null){
               break;
            }
            temporal.add(line);
			}while (line != null);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      lineas = new String[temporal.size()];
      lineas = temporal.toArray(lineas);
      return lineas;
   }

   @Override
   public void respaldar(String nombreArchivo) throws RemoteException {
      //utilizar fileread
      InputStream inputStream = null;
      OutputStream outputStream = null;
      try {
         // Nombre del archivo respaldo
         File archivoRespaldo = new File(nombreArchivo+"-respaldo");
         File archivoOriginal = new File(nombreArchivo);
         // no existe
         if (!archivoRespaldo.exists()) {
            archivoRespaldo.createNewFile();
         }

         inputStream = new FileInputStream(archivoOriginal);
         outputStream = new FileOutputStream(archivoRespaldo);
         byte[] buffer = new byte[(int)archivoOriginal.length()];

         int length;
         while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
         }
         inputStream.close();
         outputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void copiar(String nombreOrigen,String nombreArchivoDestino) throws RemoteException {
      File origen = new File(nombreOrigen);
		File destino = new File(nombreArchivoDestino);
		try {
         FileReader fr = new FileReader(origen); 
         FileWriter salida = new FileWriter(destino, true);

         BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

         String line;  
         while((line = br.readLine())!=null)  {  
            salida.append(line);      //appends line to string buffer  
            salida.append("\n");     //line feed   
         }  
               
         br.close();
         salida.close();
		} catch (IOException ioe){
		   ioe.printStackTrace();
		}
   }
   
   @Override
   public void renombrar(String nombreArchivo,String nombreNuevo) throws RemoteException {
      File f = new File(nombreArchivo);
      Path source = Paths.get(f.getAbsolutePath());

      try{
         Files.move(source, source.resolveSibling(nombreNuevo));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void eliminar(String nombreArchivo) throws RemoteException {
      try{
         File archivo = new File(nombreArchivo);
         boolean estatus = archivo.delete();
         if (!estatus) {
            System.out.println("[!] Error no se ha podido eliminar el  archivo");
         }else{
            System.out.println("[i] Se ha eliminado el archivo exitosamente");
         }
      }catch(Exception e){
         System.out.println(e);
      }
   }

   @Override
   public String getName() throws RemoteException{
      return this.name;
   }
}