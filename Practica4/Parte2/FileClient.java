import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileClient{
   public static void main(String argv[]) {
      // checando los argumentos
      if(argv.length != 1) {
        System.out.println("USO: ./client IP");
        System.exit(1);
      }

      try {
         // Obteniendo objeto Remoto
         String servidorDir = "//" + argv[0] + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(servidorDir);
         String[] archivosRemotosLista = null;
         Scanner in = new Scanner(System.in);
         int index = 0;
         String aux = null; 
         do{
            switch (menu()) {
               case 1: // Descargar Archivos
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("\nIngrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;         
                     System.out.println("\n\nDescargando archivo: "+archivosRemotosLista[index]);
                     byte[] filedata = fi.downloadFile(archivosRemotosLista[index]);
                     File file = new File(archivosRemotosLista[index]);

                     BufferedOutputStream output = new
                     BufferedOutputStream(new FileOutputStream(file.getName()));
                     output.write(filedata,0,filedata.length);
                     output.flush();
                     output.close();
                     System.out.println("Descarga completa....");
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 2: // Contar Lineas
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --; 
                     int numero = fi.cuentaLineas(archivosRemotosLista[index]);
                     System.out.println("El numero de lineas es:" + numero);
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 3:// Contar Vocales
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.print("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     System.out.println(index);
                     System.out.println(archivosRemotosLista[index]);
                     int numero = fi.cuentaVocales(archivosRemotosLista[index]);
                     System.out.println("El numero de vocales es:" + numero);
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 4: // Escribir en el archivo
                  limpia();
                  System.out.println("[i] Escribir en el archivo");
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     System.out.print("Ingrese los caracteres a escribir : \n ->  ");
                     aux = in.nextLine();
                     aux = in.nextLine();
                     boolean ban = fi.escribe(archivosRemotosLista[index], aux);
                     if (ban){
                        System.out.println("[i] Se ha escrito correctamente el archivo");
                     }else{
                        System.out.println("[!] Error, no se pudo escribir en el archivo");
                     }
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }

               break;

               case 5: // Imprimir en el archivo
                  limpia();
                  System.out.println("[i] Escribir en el archivo");
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     String[] ban = fi.imprimir(archivosRemotosLista[index]);
                     if (ban != null){
                        limpia();
                        System.out.println("[i]" + archivosRemotosLista[index]);
                        for (String string : ban) {
                           System.out.println(string);
                        }
                     }else{
                        System.out.println("[!] Error, no se pudo imprimir en el archivo");
                     }
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 6: // Copiar Archivo
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo copiar: \n -> ");
                     index = in.nextInt();
                     index --;
                     System.out.println("Ingrese el numero del archivo destino: \n -> ");
                     int index2 = in.nextInt();
                     index2 --;
                     fi.copiar(archivosRemotosLista[index],archivosRemotosLista[index2]);
                     System.out.println("[i] Archivo copiado" );
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 7: // Respaldar
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo respaldar: \n -> ");
                     index = in.nextInt();
                     index --;
                     fi.respaldar(archivosRemotosLista[index]);
                     System.out.println("[i] Respaldo completo");
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 8: // Renombrar
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     System.out.println("Ingrese nuevo nombre: \n -> ");
                     aux = in.nextLine();
                     index --;
                     fi.renombrar(aux);
                     System.out.println("[i] Archivo renombrado correctamente" );
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }

               break;

               case 9: // Eliminar
                  limpia();
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     fi.eliminar(archivosRemotosLista[index]);
                     System.out.println("[i] Archivo eliminado u.u" );
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 99: // SAlir
                  limpia();
                  System.out.println("\rSaliendo uwu\n\n");
                  in.close();
                  System.exit(0);
               break;

               default:
                  limpia();
                  System.out.println("\rOpcion invalida\n\n");
               break;
            }

         }while(true);
         

      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }

   private static String[] listar(FileInterface fi){
      System.out.println("Listando Archivos Remotos\n<---------------------------->");
      File archivos[] = null;
      try {
         archivos = fi.listar();
      } catch (RemoteException e) {
         System.out.println("[!] Error al tener los archivos remotos");
         System.exit(1);
         e.printStackTrace();
      }
      ArrayList<String> listado = new ArrayList<String>();
      if (archivos != null) {
         int i = 0;
         for (File file : archivos) {
            System.out.println("["+(i+1)+"] "+file.getName());
            listado.add(file.getName());
            i ++;
         }

      }
      String[] list = new String[listado.size()];
      list = listado.toArray(list);
      return list;
   }

   private static int menu (){
      Scanner aux = new Scanner(System.in);
      int option = 0;
      System.out.println("Selecciona una opcion uwu \n<---------------------------->");
      System.out.println("\n[1] Descargar archivo \n[2] Contar lineas");
      System.out.println("[3] Cuenta vocales \n[4] Escribe \n[5] Imprimir");
      System.out.println("[6] Copiar archivo \n[7] Respaldar \n[8] Renombrar");
      System.out.print("[9] Eliminar \n[99] Salir \n-> ");  

     
      try {
         option = aux.nextInt();
         System.out.println(option);
      } catch (Exception e) {
         System.out.println("[!] Error en la lectura del menu");
         System.exit(1);
      }
      return option;
   }

   private static void limpia(){
      System.out.print("\r");  
      System.out.print("\r\033[H\033[2J");  
      System.out.flush(); 
   }
}