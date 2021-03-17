import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class FileClient{
   private static File[] listar(FileInterface fi){
      System.out.println("Listando Archivos Remotos\n<---------------------------->");
      File archivos[] = null;
      try {
         archivos = fi.listar();
      } catch (RemoteException e) {
         System.out.println("[!] Error al tener los archivos remotos");
         System.exit(1);
         e.printStackTrace();
      }
      if (archivos != null) {
         for (File file : archivos) {
            System.out.println(file.getName());
         }
         return archivos;
      }
      return null;
   }
   public static void main(String argv[]) {
      // checando los argumentos
      if(argv.length != 1) {
        System.out.println("USO: java FileClient IP");
        System.exit(1);
      }

      try {
         // Obteniendo objeto Remoto
         String servidorDir = "//" + argv[0] + "/FileServer";
         FileInterface fi = (FileInterface) Naming.lookup(servidorDir);

         // Listando los archivo remotos
         listar(fi);

         System.out.println("\n\nDescargando archivo: "+argv[0]);
         byte[] filedata = fi.downloadFile(argv[0]);
         File file = new File(argv[0]);

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