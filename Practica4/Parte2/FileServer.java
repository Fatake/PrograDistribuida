import java.rmi.Naming;
import java.rmi.RMISecurityManager;


public class FileServer {
   public static void main(String argv[]) {
        System.out.println("\n<------Server On------->");
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            FileInterface fi = new FileImpl("hola.txt");
            System.out.println("[i] Objeto remoto creado");

            Naming.rebind("//127.0.0.1/FileServer", fi);
            System.out.println("[i] Objeto remoto subido");
        } catch(Exception e) {
            System.out.println("[!]FileServer: "+e.getMessage());
            System.out.println("\n");
            e.printStackTrace();
            System.exit(1);
        }
   }
}