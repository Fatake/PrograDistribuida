import java.net.*;
import java.io.*;

/**
 * Cliente
 */
public class UDPClient{
    public static void main(String args[]){
		// args give message contents and destination hostname

		// Crea un Socket Datagrama
		DatagramSocket aSocket = null;
		try {
			// Crea el soquet
			aSocket = new DatagramSocket();

			// Genera un ID del servidor
			InetAddress aHost = InetAddress.getByName(args[1]);

			// Puerto del servidor
			int serverPort = 6789;

			
			// Recibe mensaje por agumentos
			byte [] mensaje = args[0].getBytes(); 

			// Crea un datagrama de salida
			DatagramPacket salida = new DatagramPacket(mensaje, 
													args[0].length(), 
													aHost, 
													serverPort);

			// Envia el datagrama
			aSocket.send(salida);

			// Crea un buffer de entrada
			byte[] buffer = new byte[1000];


			// Datagrama de entrada
			DatagramPacket entrada = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(entrada);

			System.out.println("Respuesta: " + new String(entrada.getData()));

		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		}finally {
			if(aSocket != null){
				aSocket.close();
			} 
		}
	}
}
