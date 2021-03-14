package id_practica_TCP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Main {

	static private Scanner teclado = new Scanner(System.in);

	static final int PUERTO = 14147;
	static final String HOST = "localhost";
	static final String USUARIO = "alex";
	static final String PASSWORD = "admin";

	public static void main(String[] args) throws IOException {

		FTPClient ftpClient = new FTPClient();

		int opcion = 0;
		String directorio = "C:\\Users\\arbex\\OneDrive\\Escritorio\\FTP";

		ftpClient.connect(HOST);
					
			boolean isLogged = ftpClient.login(USUARIO, PASSWORD);

			if (isLogged) {

				System.out.println("Login correcto...");

			} else {

				System.out.println("Login incorrecto...");
			}


			
		System.out.println("1) Listar ficheros y directorios");
		System.out.println("2) Desconectar");
		System.out.println("3) Subir ficheros");
		System.out.println("4) Descargar fichero del servidor");
		System.out.println("5) Eliminar fichero");
		System.out.println("6) Eliminar directorio");
		System.out.println("7) Crear directorio");
		System.out.println("8) Cambiar el directorio actual");

		opcion = teclado.nextInt();
		while (opcion != 2) {

			switch (opcion) {
			case 1:

				System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
				FTPFile[] files = ftpClient.listFiles();
				System.out.println("Ficheros en el directorio actual: " + files.length);

				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i]);
				}

				break;

			case 2:

				ftpClient.disconnect();

				break;

			case 3:

				String archivo1 = "\\ejemplo.txt";
				InputStream inputstream = new FileInputStream("C:\\Users\\arbex\\OneDrive\\Escritorio\\FTP");
				ftpClient.storeFile(archivo1, inputstream);

				break;
				
			case 4:

				String archivo2 = "\\caca.txt";
				OutputStream outputStream = new FileOutputStream("C:\\Users\\arbex\\OneDrive\\Escritorio\\FTP\\caca.txt");
				ftpClient.retrieveFile(archivo2, outputStream);

				break;
				
			case 5:

				System.out.println("Dime el nombre del fichero que quieres borrar");
				directorio = teclado.nextLine();
				directorio = teclado.nextLine();
				ftpClient.deleteFile(directorio);

				break;
				
			case 6:

				System.out.println("Dime el nombre del directorio que quieres crear");
				directorio = teclado.nextLine();
				directorio = teclado.nextLine();
				ftpClient.removeDirectory(directorio);

				break;
				
			case 7:
				System.out.println("Dime el nombre del directorio que quieres crear");
				directorio = teclado.nextLine();
				directorio = teclado.nextLine();
				ftpClient.makeDirectory(directorio);

				break;
				
			case 8:

				System.out.println("Dime el nombre del directorio al que quieres ir");
				directorio = teclado.nextLine();
				directorio = teclado.nextLine();

				if (directorio.equals("..")) {
					
					ftpClient.changeToParentDirectory();

				} else {
					ftpClient.changeWorkingDirectory(directorio);

				}

				System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());

				break;

			}

			System.out.println("1) Listar ficheros y directorios");
			System.out.println("2) Desconectar");
			System.out.println("3) Subir ficheros");
			System.out.println("4) Descargar fichero del servidor");
			System.out.println("5) Eliminar fichero");
			System.out.println("6) Eliminar directorio");
			System.out.println("7) Crear directorio");
			System.out.println("8) Cambiar el directorio actual");

			opcion = teclado.nextInt();
			
		}
	}
}
