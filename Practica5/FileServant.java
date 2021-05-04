import java.io.*; 

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
}