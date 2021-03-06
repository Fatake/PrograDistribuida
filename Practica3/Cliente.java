import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Cliente {

	// Indicar la direccion y el numero de puerto donde escuchar el proceso servidor
	static private String SERVER_ADDRESS = "localhost";
	static private int SERVER_PORT = 8080;

	// Creacion del socket con el que se llevara a cabo la comunicacion con el servidor.
	static private Socket socketAlServidor = null;

	static private boolean conectarServidor(int maxIntentos){
		//hasta maxIntentos intentos de conexion, para darle tiempo al servidor a arrancar
		boolean exito = false;     // hay servidor?
		int van = 0;

		while((van<maxIntentos) && !exito){
			try {
				socketAlServidor = new Socket(SERVER_ADDRESS, SERVER_PORT);
				exito = true;
			} catch (Exception e) {
				van++;
				System.err.println("[!] Fallo #" + van);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
			}
		}
		return exito;
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("[!] Lance el comando ./cliente IP PORT");
			System.exit(1);  
		}

		SERVER_ADDRESS = args[0];
		SERVER_PORT = Integer.parseInt(args[1]);

		System.out.println("[+] Iniciando Cliente... ");

		//10 intentos
		if(!conectarServidor(3)){
			System.err.println("[!] Error, no se puede conectar con el Servidor: " + SERVER_ADDRESS);
			System.exit(1);           //abortar si hay problemas
		}

		// ya hay conexcion
		// Inicializar los flujos de datos del socket para la comunicacion con el servidor

		PrintWriter canalSalidaAlServidor = null;
		BufferedReader canalEntradaDelServidor = null;
		try {
			canalSalidaAlServidor = new PrintWriter(socketAlServidor.getOutputStream(),true);
			canalEntradaDelServidor = new BufferedReader(new InputStreamReader(socketAlServidor.getInputStream()));
		} catch (IOException e) {      //abortar si hay problemas
			System.err.println("I/O problem:" + SERVER_ADDRESS);
			System.exit(1);
		}
		// Un buffer de entrada para leer de la entrada standard.
		BufferedReader entradaStandard = new BufferedReader(new InputStreamReader(System.in));
		String userInput = "";

		// Protocolo de comunicacion con el Servidor.
		// Mientras no se reciba la secuencia "END OF SERVICE"
		// el servidor contara las vocales que aparecen en las frases
		// que le enviar el cliente.
		// El cliente obtiene las frases
		// que le pasa al servidor del usuario que lo esta ejecutando.
		try{
			while (!(userInput.equals("Salida!"))) {
				System.out.print("Texto: ");
				userInput = entradaStandard.readLine();
				if (userInput != null) {
					canalSalidaAlServidor.println(userInput);
					String respuesta = canalEntradaDelServidor.readLine();
					if (respuesta != null) {
						System.out.println("Server answer: " + respuesta);
					} else {
						System.out.println("Comm. is closed!");
					}
				} else {
					System.err.println("Wrong input!");
				}
			}

			// Al cerrar cualquiera de los canales de comunicacion utilizados por un socket,este se cierra.
			// cerrar el canal de entrada.
			canalEntradaDelServidor.close();

			// cerrar el Socket de comunicacion con el servidor.
			socketAlServidor.close();
		} catch (Exception e){
			System.err.println(e);
		}

	}
}
