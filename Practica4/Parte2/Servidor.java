import java.rmi.Naming;

public class Servidor {
  /**
   * Server program for the "Hello, world!" example.
   * @param argv The command line arguments which are ignored.
   */
  public static void main (String[] argv) {
    System.out.println("[i] Iniciando Servidor RMI");
    try {
      Naming.rebind ("rmi://localhost:1099/Hola", new Hello ("Prueba de clientes remotos"));
      System.out.println ("[i] Registro con Naming Completo.");
    } catch (Exception e) {
      System.out.println ("[i] Fallo en el registro " + e);
    }
  }
}
