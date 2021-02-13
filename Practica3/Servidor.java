import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Servidor {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("[!] Lance el Comando ./servidor PORT");
			System.exit(1);  
		}

		int SERVER_PORT = Integer.parseInt(args[0]);


		ServerSocket serverSocket = null; // para escuchar
		Socket clientSocket = null;       // uno por cliente
		
		System.out.println("[+] Iniciando Servidor... por el puerto: "+SERVER_PORT);

		try{
			// Inicia La escucha
			serverSocket = new ServerSocket(SERVER_PORT);

			// Acepta Peticiones
			do {
				try {
					clientSocket = serverSocket.accept();
				} catch (IOException e) {
					serverSocket.close();
					break;
				}

				Thread t = new HiloCliente(clientSocket);
				t.start();
			}while(true);
		} catch (Exception e) {
			System.out.println("[!] Error En Hilo Principal del Servidor");
		}

		System.out.println("[i] Cerrando Servidor\n\n");
		try {
			serverSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}
}