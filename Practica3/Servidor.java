import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Servidor {

	//Crear un socket servidor
	//si no lo logra abortar programa
	private static ServerSocket creaListenSocket(int serverSockNum){
		ServerSocket server = null;

		try{
    		server = new ServerSocket(serverSockNum);
  		} catch (IOException e) {
   			System.err.println("Problems in port: " + serverSockNum);
   			System.exit(-1);
   		}
   		return server;
  	}

  	//Establecer conexi�n con el servidor y devolver socket
  	//si no lo logra abortar programa
  	private static Socket creaClientSocket(ServerSocket server){
  		Socket res = null;

  		try {
			res = server.accept();
			System.out.println("[i] Aceptando Coneccion de:"+res.getInetAddress());
		} catch (IOException e) {
			System.err.println("[!] Coneccion Fallida");
			System.exit(1);
		}
		return res;
  	}

  	//Devuelve la cantidad de vocales de la frase
	private static int numeroDeVocales(String frase) {
		int res = 0;
		String fraseMin = frase.toLowerCase();

		for (int i = 0; i < fraseMin.length(); ++i) {
    		switch(fraseMin.charAt(i)) {
        		case 'a':
        		case 'e': 
        		case 'i':
        		case 'o':
        		case 'u':
            		res++;
            		break;
        		default:
            		// se ignoran las dem�s letras
   			}
   		}
		return res;
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("[!] Lance el Comando ./servidor PORT");
			System.exit(1);  
		}
		int SERVER_PORT = Integer.parseInt(args[0]);
		// El servidor escuchar� peticiones en local
		// en el puerto SERVER_PORT (>= 1024)
		ServerSocket serverSocket = null; //para escuchar
		Socket clientSocket = null;       //uno por cliente
		
		System.out.println("[+] Iniciando Servidor... por el puerto "+SERVER_PORT);


		// Inicializar el socket del cliente con el que se va
		// a comunicar el servidor, es decir se acepta la
		// conexi�n de un cliente al servidor mediante
		// el m�todo accept()
		serverSocket = creaListenSocket(SERVER_PORT);

		// En este ejemplo, s�lo uno. En un caso general
		// un servidor tendr�a esto en un ciclo, creando
		// uno por cada nuevo cliente
		clientSocket = creaClientSocket(serverSocket);

		// Inicializar los canales de comunicaci�n utilizados en
		// el socket para comunicarse con el cliente.
		// El OutputStream permite enviar mensajes al cliente
		// El InputStream permite recibir mensajes emitidos
		// por el proceso cliente

		PrintWriter salHaciaCliente = null;
		BufferedReader entDesdeCliente = null;

		try{
			salHaciaCliente = new PrintWriter(clientSocket.getOutputStream(), true);
			entDesdeCliente = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}

		// Contar las vocales de las frases enviadas por el cliente
		String inputLine = "";
		try{
			inputLine = entDesdeCliente.readLine();

			while ((inputLine != null) && (!inputLine.equals("Salir!"))) {
				// Contar la cantidad de vocales que

				String respuesta = "'" + inputLine + "' Tiene " + + numeroDeVocales(inputLine) + " Vocales";

				// Enviar la respuesta al cliente
				salHaciaCliente.println(respuesta);

				// Recibir nueva petici�n del cliente
				inputLine = entDesdeCliente.readLine();
			}
			// Al cerrar cualquier canal de comunicaci�n
			// utilizado por un socket, �ste se cierra.
			// Para asegurarse que se env�en las respuestas que
			// est�n en el buffer se cierra el OutputStreams
			salHaciaCliente.close();

			clientSocket.close();

		}catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}

		System.out.println("[i] Cerrando Coneccion "+clientSocket.getInetAddress());

	}
}