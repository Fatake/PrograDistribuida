import java.rmi.Naming;
import java.rmi.RMISecurityManager;


public class FileServer {
   public static void main(String argv[]) {
        System.out.println("Iniciando....");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            FileInterface fi = new FileImpl("hola.txt");
            Naming.rebind("//127.0.0.1/FileServer", fi);
            System.out.println("Cargando el archivo en sistema....");
        } catch(Exception e) {
            System.out.println("FileServer: "+e.getMessage());
            e.printStackTrace();
        }
   }
}