import java.net.*;
import java.io.*;

/**
 * 
 */
public class UDPServer{

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
				Integer cantidad_vocales = new Integer (numeroDeVocales(mensaje));
				byte[] salida = cantidad_vocales.byteValue();

				// Envia datos a un nueva datagrama 
				// donde DatagramPacket(buffer,lenght, addres, port)
				// buffer y lenght es lo que se va a enviar y el tamaño
				// address del destino
				// port puerto del destino
				DatagramPacket reply = new DatagramPacket(salida,
														salida.getLength(), 
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
