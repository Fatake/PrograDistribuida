import java.net.*;
import java.io.*;

/**
 * 
 */
public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
		try{
	    	aSocket = new DatagramSocket(6789);
			
			// create socket at agreed port
			byte[] buffer = new byte[1000];

			// Sigue escuchando peticiones
			while(true){
				// buffer <- Es lo que se va a leer o escribir
				// lenght es cuando se va a leer o escribir
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);

				// Recibe datos
				aSocket.receive(request);
				String mensaje = new String(request.getData());
				System.out.println("[i] Recibiendo: "+mensaje);

				// Envia datos a un nueva datagrama 
				// donde DatagramPacket(buffer,lenght, addres, port)
				// buffer y lenght es lo que se va a enviar y el tamaño
				// address del destino
				// port puerto del destino
				DatagramPacket reply = new DatagramPacket(request.getData(),
														request.getLength(), 
														request.getAddress(), 
														request.getPort());
				
				// envia el datagrama
				aSocket.send(reply);
			}
		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}finally {
			if(aSocket != null){
				aSocket.close();
			}	
		}
    }
}
