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
         do{
            switch (menu) {
               case 0:
                     // Listando los archivo remotos
                     archivosRemotosLista = listar(fi);
               break;
               case 1:
                  int index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
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
                  }
               break;
               case 2:
                  int index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     in.close();   
                     int numero = fi.cuentaLineas(archivosRemotosList[index]);
                     System.out.println("El numero de lineas es:" + numero);
                  }
               break;
               case 3:
                  int index = 0;
                  if (archivosRemotosLista.length != 0) {
                     System.out.println("Ingrese el numero del archivo: \n -> ");
                     index = in.nextInt();
                     index --;
                     in.close();   
                     int numero = fi.cuentaVocales(archivosRemotosList[index]);
                     System.out.println("El numero de vocales es:" + numero);
                  }

               break;
               case 4:
                  

               break;
               case 5:

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
      System.out.println("[0] Listar \n [1] Descargar archivo \n [2] Contar lineas \n [3] Cuenta vocales \n [4] Escribe 
      \n [5] Imprimir \n [6] Copiar archivo \n [7] Respaldar \n [8] Renombrar \n [9] Eliminar \n [99] Salir ");   
      return (new Scanner(System.in)).nextInt();
   }

   
}