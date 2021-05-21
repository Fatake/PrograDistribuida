import java.io.*;
import java.nio.*;
import java.util.*;

public class FileServant extends _FileInterfaceImplBase {

   public byte[] downloadFile(String fileName){
      File file = new File(fileName);
      byte buffer[] = new byte[(int)file.length()];
      try {
         BufferedInputStream input = new
           BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
      } catch(Exception e) {
         System.out.println("FileServant Error: "+e.getMessage());
         e.printStackTrace();
      }
      return(buffer); 
   }

   public File[] listar(){  
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

   public int cuentaLineas(String nombreArchivo){ 
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
   
   public int cuentaVocales(String nombreArchivo){
      BufferedReader reader = null;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(nombreArchivo));
   
			String line = "";
         
			do {
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
            e.printStackTrace();
         }
      } 
      return contador;
   }

   public Boolean escribe(String destino, String mensaje){
		File fDestino = new File(destino);
		try {
		   OutputStream os = new FileOutputStream(fDestino,true);
         os.write("\n".getBytes(),0,"\n".length());
		   os.write(mensaje.getBytes(),0,mensaje.length());
		   os.close();
		} catch (IOException ioe){
		   ioe.printStackTrace();
         return false;
		}
      return true;
   }

   public String[] imprimir(String archivo){
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

   public void respaldar(String nombreArchivo){
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

   public void copiar(String nombreOrigen,String nombreArchivoDestino){
      File origen = new File(nombreOrigen);
		File destino = new File(nombreArchivoDestino);
		try {
         FileReader fr = new FileReader(origen);
         FileWriter salida = new FileWriter(destino, true);

         BufferedReader br = new BufferedReader(fr);
         String line;
         salida.append("\n");
         while((line = br.readLine())!=null)  {  
            salida.append(line);
            salida.append("\n");
         }  
               
         br.close();
         salida.close();
		} catch (IOException ioe){
		   ioe.printStackTrace();
		}
   }
   
   public void renombrar(String nombreArchivo,String nombreNuevo){
      File f = new File(nombreArchivo);
      Path source = Paths.get(f.getAbsolutePath());

      try{
         Files.move(source, source.resolveSibling(nombreNuevo));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void eliminar(String nombreArchivo){
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
}