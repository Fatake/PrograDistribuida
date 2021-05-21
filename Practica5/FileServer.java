import java.io.*;
import java.nio.*;
import java.util.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class FileServer {
   public static void main(String args[]) {
      try{
         // create and initialize the ORB
         ORB orb = ORB.init(args, null);
         // create the servant and register it with the ORB
         FileServant fileRef = new FileServant();
         orb.connect(fileRef);
         // get the root naming context
         org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
         NamingContext ncRef = NamingContextHelper.narrow(objRef);
         // Bind the object reference in naming
         NameComponent nc = new NameComponent("FileTransfer", " ");
         NameComponent path[] = {nc};
         ncRef.rebind(path, fileRef);

         System.out.println("[i] Servidor inciando\n[+] Escuchando...");
         // Wait for invocations from clients
         java.lang.Object sync = new java.lang.Object();
         synchronized(sync){
            System.out.println("[+] Nuevo cliente: "+sync.toString());
            sync.wait();
         }
      } catch(Exception e) {
         System.err.println("[!] ERROR: " + e.getMessage());
         e.printStackTrace(System.out);
      }
   }
}