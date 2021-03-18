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

         do{
            switch (menu()) {
               case 1: // Descargar Archivos
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("\nIngrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     in.close();           
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
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     in.close();   
                     int numero = fi.cuentaLineas(archivosRemotosLista[index]);
                     System.out.println("El numero de lineas es:" + numero);
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 3:// Contar Vocales
                  archivosRemotosLista = listar(fi);
                  index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     in.close();   
                     int numero = fi.cuentaVocales(archivosRemotosLista[index]);
                     System.out.println("El numero de vocales es:" + numero);
                  }else{
                     System.out.println("[!] No existen archivos en el servidor");
                  }
               break;

               case 4: // Escribir en el archivo
                  

               break;

               case 5: // Imprimir en el archivo

               break;
               case 6:

               break;
               case 7:

               break;
               case 8:

               break;
               case 9:

               break;
               case 99:
                     System.exit(0);
               break;
               default:
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
      System.out.println("Selecciona una opcion uwu \n<---------------------------->");
      System.out.println("\n[1] Descargar archivo \n[2] Contar lineas");
      System.out.println("\n[3] Cuenta vocales \n[4] Escribe \n[5] Imprimir");
      System.out.println("\n[6] Copiar archivo \n[7] Respaldar \n[8] Renombrar");
      System.out.println("\n[9] Eliminar \n[99] Salir \n-> ");  

      int option = 0;
      try {
         Scanner aux = new Scanner(System.in);
         option = aux.nextInt();
         aux.close();
      } catch (Exception e) {
         System.out.println("[!] Error en la lectura del menu");
         System.exit(1);
      }
      return option;
   }

   
}