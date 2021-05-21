import java.io.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class FileClient {
   public static void main(String argv[]) {
      try {
         System.out.println("[i] Iniciando ORB server");
         // create and initialize the ORB
         ORB orb = ORB.init(argv, null);

         System.out.println("[+] Buscando NameService");
         // get the root naming context
         org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
         NamingContext ncRef = NamingContextHelper.narrow(objRef);
         NameComponent nc = new NameComponent("FileTransfer", " ");     
         
         System.out.println("[i] Obteniendo path");
         // Resolve the object reference in naming
         NameComponent path[] = {nc};

         System.out.println("[i] Objeto remoto econtrado");
         FileInterfaceOperations fileRef = FileInterfaceHelper.narrow(ncRef.resolve(path));

         //
         // Menu
         //
         do{
            switch (menu()) {
               case 0: // Listar
                  limpia();
                  listar(fileRef);
               break;
               
               case 1: // Descargar Archivos
                  limpia();
                  descargar(fileRef);
               break;

               case 2: // Contar Lineas
                  limpia();
                  contarLineas(fileRef);
               break;

               case 3:// Contar Vocales
                  limpia();
                  contarVocales(fileRef);
               break;

               case 4: // Escribir en el archivo
                  limpia();
                  escribirArchivo(fileRef);
               break;

               case 5: // Imprimir en el archivo
                  limpia();
                  imprimir(fileRef);
               break;

               case 6: // Copiar Archivo
                  limpia();
                  copiar(fileRef);
               break;

               case 7: // Respaldar
                  limpia();
                  respaldar(fileRef);
               break;

               case 8: // Renombrar
                  limpia();
                  break;
                  renombrar(fileRef);
               break;

               case 9: // Eliminar
                  limpia();
                  eliminar(fileRef);
               break;

               case 99: // Salir
                  limpia();
                  System.out.println("\r[i] Saliendo...\n\n");
                  System.exit(0);
               break;

               default:
                  limpia();
                  System.out.println("\rOpcion invalida\n\n");
               break;
            }
         }while(true);
      } catch(Exception e) {
         System.out.println("FileClient Error: " + e.getMessage());
         e.printStackTrace();
      }
   }

   private static void eliminar(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));


      int index = 0;
      
      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("Ingrese el numero del archivo: \n -> ");
         index = Integer.parseInt(in.readLine());
         index --;
         fileRef.eliminar(archivosRemotosLista[index]);
         System.out.println("[i] Archivo eliminado u.u" );
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void renombrar(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;
      String aux = null; 

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("Ingrese el numero del archivo: \n -> ");
         index = Integer.parseInt(in.readLine());
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         System.out.print("Ingrese nuevo nombre: \n -> ");
         aux = in.readLine();

         
         fileRef.renombrar(archivosRemotosLista[index],aux);
         System.out.println("[i] Archivo renombrado correctamente" );
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void respaldar(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("Ingrese el numero del archivo respaldar: \n -> ");
         index = Integer.parseInt(in.readLine());
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         fileRef.respaldar(archivosRemotosLista[index]);
         System.out.println("[i] Respaldo completo");
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void copiar(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("[+] Ingrese el numero del archivo copiar: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         System.out.print("[+] Ingrese el numero del archivo destino: \n -> ");
         name = in.readLine();

         int index2 = Integer.parseInt(name);
         index2 --;
         fileRef.copiar(archivosRemotosLista[index],archivosRemotosLista[index2]);
         System.out.println("[i] Archivo copiado" );
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void imprimir(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("[+] Ingrese el numero del archivo a Imprimir: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         String[] ban = fileRef.imprimir(archivosRemotosLista[index]);
         if (ban != null){
            limpia();
            System.out.println("[i]" + archivosRemotosLista[index]);
            for (String string : ban) {
               System.out.println(string);
            }
         }else{
            System.out.println("[!] Error, no se pudo imprimir en el archivo");
         }
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void escribirArchivo(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;
      String aux = null; 

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("[+] Ingrese el numero del archivo a Imprimir: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         System.out.print("[+] Ingrese los caracteres a escribir : \n ->  ");
         name = in.readLine();

         boolean ban = fileRef.escribe(archivosRemotosLista[index], name);
         if (ban){
            System.out.println("[i] Se ha escrito correctamente el archivo");
         }else{
            System.out.println("[!] Error, no se pudo escribir en el archivo");
         }
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void contarVocales(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("[-] Ingrese el numero del archivo a Contar Vocales: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);
         index --;
         if (index > archivosRemotosLista.length) {
            break;
         }
         int numero = fileRef.cuentaVocales(archivosRemotosLista[index]);
         System.out.println("[i] El numero de vocales es:" + numero);
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void contarLineas(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));

      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("[-] Ingrese el numero del archivo a leer Lineas: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);
         index --; 
         if (index > archivosRemotosLista.length) {
            System.out.println("[!] No existe ese archivo");
            break;
         }
         int numero = fileRef.cuentaLineas(archivosRemotosLista[index]);
         System.out.println("[-] El numero de lineas es:" + numero);
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static void descargar(FileInterfaceOperations fileRef) {
      String[] archivosRemotosLista = null;
      BufferedReader in = new BufferedReader(
         new InputStreamReader(System.in));


      int index = 0;

      archivosRemotosLista = listar(fileRef);
      if (archivosRemotosLista.length != 0) {
         System.out.print("\nIngrese el numero del archivo a descargar: \n -> ");
         String name = in.readLine();

         index = Integer.parseInt(name);  
         index --;  
         if (index > archivosRemotosLista.length) {
            System.out.println("[!] Ese archivo no existe");
            break;
         } 
         System.out.println("\n\n[+]Descargando archivo... "+archivosRemotosLista[index]);
         byte[] filedata = fileRef.downloadFile(archivosRemotosLista[index]);
         File file = new File(archivosRemotosLista[index]);

         BufferedOutputStream output = new
         BufferedOutputStream(new FileOutputStream(file.getName()));

         output.write(filedata,0,filedata.length);
         output.flush();
         output.close();
         System.out.println("[i] Descarga completa");
      }else{
         System.out.println("[!] No existen archivos en el servidor");
      }
   }

   private static String[] listar(FileInterfaceOperations fileRef) {
      System.out.println("\n<---------------------------->");
      System.out.println("\t<----Archivos Remotos---->");
      System.out.println("<---------------------------->");

      File archivos[] = null;
      try {
         archivos = fileRef.listar();
      } catch (Error e) {
         System.out.println("[!] Error al tener los archivos remotos");
         System.exit(1);
         e.printStackTrace();
      }
      String [] list = new String[100];
      if (archivos != null) {
         int i = 0;
         for (File file : archivos) {
            System.out.println("["+(i+1)+"] "+file.getName());
            list[i] = file.getName();
            i ++;
         }
      }
      return list;
   }

   private static int menu (){
      BufferedReader aux = new BufferedReader(
         new InputStreamReader(System.in));

      int option = 0;
      System.out.println("\n<---------------------------->");
      System.out.println("\t<-------Menu---------->");
      System.out.println("<---------------------------->");
      System.out.println("Seleccione una opcion:\n");
      System.out.println("\n[1] Descargar archivo \n[2] Contar lineas");
      System.out.println("[3] Cuenta vocales \n[4] Escribe \n[5] Imprimir");
      System.out.println("[6] Copiar archivo \n[7] Respaldar \n[8] Renombrar");
      System.out.print("[9] Eliminar \n[99] Salir \n-> ");  

      try {
         String name = aux.readLine();
         option = Integer.parseInt(name);  
      } catch (Exception e) {
         System.out.println("[!] Error en la lectura del menu");
         System.exit(1);
      }

      return option;
   }

   private static void limpia(){
      System.out.print("\r");  
      System.out.print("\r\033[H\033[2J");  
      System.out.flush(); 
   }
}