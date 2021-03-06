import java.rmi.Naming;

public class Cliente {
  /**
   * Programa Main Cliente
   * @param argv
   */
  public static void main (String[] argv) {

    System.out.println("[i] Iniciando Cliente RMI");

    try {
      IOArchivoInterface remoto = (IOArchivoInterface) Naming.lookup ("rmi://localhost:1099/Hola");
      System.out.println (remoto.say());
    } catch (Exception e) {
      System.out.println ("[!] Error en el cliente: " + e);
    }
  }
}
