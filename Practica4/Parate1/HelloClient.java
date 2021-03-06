import java.rmi.Naming;

public class HelloClient {

  /**
   * Client program for the "Hello, world!" example.
   * @param argv The command line arguments which are ignored.
   */
  public static void main (String[] argv) {
    System.out.println("[i] Iniciando Cliente RMI");
    try {
      System.out.println("[i] Lista de Objetos remotos");
      String lista[] = Naming.list("rmi://localhost:1099/Hola");
      for (String objeto : lista) {
        System.out.println("[+] "+ objeto);
      }

      HelloInterface hello = (HelloInterface) Naming.lookup ("rmi://localhost:1099/Hola");
      System.out.println (hello.say());
    } catch (Exception e) {
      System.out.println ("HelloClient exception: " + e);
    }
  }
}
