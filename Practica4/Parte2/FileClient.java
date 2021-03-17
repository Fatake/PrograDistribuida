import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileClient{
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

         // Listando los archivo remotos
         String[] archivosRemotosLista =  listar(fi);
         int index = 0;
         if (archivosRemotosLista.length != 0) {
            System.out.println("Ingrese el archivo que quiere descargar(Numero)");
            Scanner in = new Scanner(System.in);
            index = in.nextInt();
            index --;
            in.close();
         }

         System.out.println("\n\nDescargando archivo: "+archivosRemotosLista[index]);
         byte[] filedata = fi.downloadFile(archivosRemotosLista[index]);
         File file = new File(archivosRemotosLista[index]);

         BufferedOutputStream output = new
         BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata,0,filedata.length);
         output.flush();
         output.close();
         System.out.println("Descarga completa....");
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}