import java.rmi.Naming;
import java.rmi.RMISecurityManager;


public class FileServer {
   public static void main(String argv[]) {
        System.out.println("[i] Iniciando....");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            FileInterface fi = new FileImpl("hola.txt");
            Naming.rebind("//127.0.0.1/FileServer", fi);
            System.out.println("[i] Servidor Cargado.");
        } catch(Exception e) {
            System.out.println("\nFileServer: "+e.getMessage());
            e.printStackTrace();
        }
   }
}