import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HiloCliente extends Thread{
    private Socket cliente;
    private PrintWriter salHaciaCliente;
    private BufferedReader entDesdeCliente ;

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
   			}
   		}
		return res;
	}

    HiloCliente(Socket clientSocket) {
		this.cliente = clientSocket;
        System.out.println("[i] Aceptando Coneccion de: \n->"+clientSocket);

		try{
			this.salHaciaCliente = new PrintWriter(this.cliente .getOutputStream(), true);
			this.entDesdeCliente = new BufferedReader(new InputStreamReader(this.cliente .getInputStream()));
		}catch (IOException e) {
            System.out.println("[!] Problema al iniciar los Pipes");
			System.err.println(e);
			System.exit(1);
		}

	}

    @Override
    public void run() {
        // Contar las vocales de las frases enviadas por el cliente
		String inputLine = "";
		try{
			inputLine = this.entDesdeCliente.readLine();

			while ((inputLine != null) && (!inputLine.equals("Salir!"))) {
				// Contar la cantidad de vocales que

				String respuesta = "'" + inputLine + "' Tiene " + + numeroDeVocales(inputLine) + " Vocales";

				// Enviar la respuesta al cliente
				this.salHaciaCliente.println(respuesta);

				// Recibir nueva peticion del cliente
				inputLine = entDesdeCliente.readLine();
			}
			salHaciaCliente.close();

			this.cliente.close();

		}catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
		System.out.println("[i] Cerrando Coneccion: \n-> "+this.cliente);
    }
}
