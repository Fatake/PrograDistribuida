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
         FileInterface fi = (FileInterface) Naming.lookup(name);
         byte[] filedata = fi.downloadFile(argv[0]);
         File file = new File(argv[0]);
         BufferedOutputStream output = new
         BufferedOutputStream(new FileOutputStream(file.getName()));
         output.write(filedata,0,filedata.length);
         output.flush();
         output.close();
      } catch(Exception e) {
         System.err.println("FileServer exception: "+ e.getMessage());
         e.printStackTrace();
      }
   }
}