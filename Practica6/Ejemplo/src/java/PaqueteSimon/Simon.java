/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteSimon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author gato_
 */
@WebService(serviceName = "Simon")
public class Simon {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "CuentaLineas")
    public Integer CuentaLineas(@WebParam(name = "nombreArchivo") String nombreArchivo) {
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CuentaVocales")
    public Integer CuentaVocales(@WebParam(name = "NombreArchivo") String NombreArchivo) {
BufferedReader reader = null;
      int contador = 0;
		try {
			reader = new BufferedReader(new FileReader(NombreArchivo));
   
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "EscribirArchivo")
    public Boolean EscribirArchivo(@WebParam(name = "Destino") String Destino, @WebParam(name = "Mensaje") String Mensaje) {
        File fDestino = new File(Destino);
		try {
		   OutputStream os = new FileOutputStream(fDestino,true);	
		   os.write(Mensaje.getBytes(),0,Mensaje.length());
		   os.close();
      
		} catch (IOException ioe){
		   ioe.printStackTrace();
         return false;
		}
      return true;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "Respaldar")
    public Boolean Respaldar(@WebParam(name = "nombreArchivo") String nombreArchivo) {
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
      return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Copiar")
    public Boolean Copiar(@WebParam(name = "NombreOrigen") String NombreOrigen, @WebParam(name = "NombreArchivoDestino") String NombreArchivoDestino) {
    File origen = new File(NombreOrigen);
		File destino = new File(NombreArchivoDestino);
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
        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Renombrar")
    public Boolean Renombrar(@WebParam(name = "NombreArchivo") String NombreArchivo, @WebParam(name = "NombreNuevo") String NombreNuevo) {
        File f = new File(NombreArchivo);
      Path source = Paths.get(f.getAbsolutePath());

      try{
         Files.move(source, source.resolveSibling(NombreNuevo));
      } catch (IOException e) {
         e.printStackTrace();
      }
      return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Eliminar")
    public Boolean Eliminar(@WebParam(name = "NombreArchivo") String NombreArchivo) {
         try{
         File archivo = new File(NombreArchivo);
         boolean estatus = archivo.delete();
         if (!estatus) {
            System.out.println("[!] Error no se ha podido eliminar el  archivo");
         }else{
            System.out.println("[i] Se ha eliminado el archivo exitosamente");
         }
      }catch(Exception e){
         System.out.println(e);
      }
      return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Imprimir")
    public String[] Imprimir(@WebParam(name = "NombreArchivo") String NombreArchivo) {
        BufferedReader reader;
      File Farchivo = new File(NombreArchivo);
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Listar")
    public String Listar() {
        File[] archivos  = null;
        try {
            File dir = new File(".");
            if(dir.exists()) {
                if(dir.isDirectory()) {
                    archivos = dir.listFiles();
                }
            }
        } catch(Exception e){
         
         e.printStackTrace();
         return "Error";
        }

        ArrayList<String> listado = new ArrayList<String>();
        if (archivos != null) {
         int i = 0;
         for (File file : archivos) {
                        //System.out.println("["+(i+1)+"] "+file.getName());
                        listado.add(file.getName());
                        i ++;
         }
        }
        if(listado.isEmpty()){
            return "no archivos";
        }
        String[] list = new String[listado.size()];
        list = listado.toArray(list);

        String names = "";
        for(String nombre:list){
        names += nombre + " ";
        }
        return names;
    }
    
}
