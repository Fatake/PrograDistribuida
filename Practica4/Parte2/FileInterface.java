import java.io.File;
import java.io.OutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileInterface extends Remote {

	public byte[] downloadFile(String fileName) throws RemoteException;
	public File[] listar() throws RemoteException;

	// Paulo
	// que devuelva el numero de líneas del archivo
	int cuentaLineas (String nombreArchivo) throws RemoteException;

	// Paulo
	// que devuelva el numero de Vocales del archivo
	int cuentaVocales (String nombreArchivo) throws RemoteException;
	
	// In	
	// que escriba el contenido del archivo a os
	void escribe (OutputStream os) throws RemoteException;

	// In 
	// que imprima el contenido del archivo a pantalla

	void imprimir () throws RemoteException;
	
	// In
	// que respalde el archivo
	void respaldar(String nombreArchivo) throws RemoteException;

	// Paulo
	// que copie el contenido de un archivo 
	// fuente a un archivo destino nombrearchivodestino
	void copiar (String nombreArchivoDestino) throws RemoteException; 

	// Paulo
	// que renombre el archivo
	void renombrar(String nombreArchivo) throws RemoteException;

	// In
	// que elimine el archivo de almacenamiento masivo.
	void eliminar(String nombreArchivo) throws RemoteException;

	String getName();

}
