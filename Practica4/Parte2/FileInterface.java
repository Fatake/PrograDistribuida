import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileInterface extends Remote {
	public byte[] downloadFile(String fileName) throws
	RemoteException;
	public File[] listar() throws RemoteException;

	// Paulo
	// que devuelva el número de líneas del archivo
	int cuentaLineas (String nombrearchivo) throws RemoteException;

	// Paulo
	// que devuelva el número de Vocales del archivo
	int cuentavocales (String nombrearchivo) throws RemoteException;
	
	// In	
	// que escriba el contenido del archivo a os
	void escribe (OutputStream os) throws RemoteException;

	// In 
	// que imprima el contenido del archivo a pantalla

	void imprimir () throws RemoteException;
	
	// In
	// que respalde el archivo
	void respaldar(String nombrearchivo) throws RemoteException;

	// Paulo
	// que copie el contenido de un archivo 
	// fuente a un archivo destino nombrearchivodestino
	void copiar (String nombrearchivodestino) throws RemoteException; 

	// Paulo
	// que renombre el archivo
	void renombrar(String nombrearchivo) throws RemoteException;

	// In
	// que elimine el archivo de almacenamiento masivo.
	void eliminar(String nombrearchivo) throws RemoteException;

}