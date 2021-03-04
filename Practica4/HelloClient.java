import java.rmi.*;

public class HelloClient{

  /**
   * Client program for the "Hello, world!" example.
   * @param argv The command line arguments which are ignored.
   */
  public static void main (String[] argv) {
    System.out.println("[i] Iniciando Cliente RMI");
    try {
      HelloInterface hello = (HelloInterface) Naming.lookup ("//localhost/Hello");
      System.out.println (hello.say());
    } catch (Exception e) {
      System.out.println ("HelloClient exception: " + e);
    }
  }
}
