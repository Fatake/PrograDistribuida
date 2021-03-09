import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;

public class FileClient{
   public static void main(String argv[]) {
      if(argv.length != 2) {
        System.out.println("USO: java FileClient fileName machineName");
        System.exit(0);
      }
      try {
         String name = "//" + argv[1] + "/FileServer";
         System.out.println("Buscando Objetos Remotos....");
         FileInterface fi = (FileInterface) Naming.lookup(name);

         System.out.println("Listando Archivos Remotos....");
         File archivos[] = fi.listar();
         if (archivos != null) {
            for (File file : archivos) {
               System.out.println(file.getName());
            }
         }

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