// Imports
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * Server class
 */
public class Server {

	private static final int fail = 1;
	private static final int success = 0;

	private int listeningPort = 6500;
	private ServerSocket serverSocket = null;
	ExecutorService service = null;

	FileWriter fptr = null;

	public Server() {
		try {
			serverSocket = new ServerSocket(listeningPort);
		}
		catch (IOException error) {
			System.err.println("Could not listen on port: " + listeningPort);
			System.exit(fail);
		}
	}
	public void run () {

		Socket clientSock = null;
		service = Executors.newFixedThreadPool(30);

		while (true) {

			try {
				clientSock = serverSocket.accept();
				service.submit(new ClientHandler(clientSock));

			} catch (IOException error) {
				System.err.println("could not listen");
				System.exit(fail);
			}
		}
	}

	/**
	 * Server program start
	 * @param args
	 */
	public static void main( String[] args ) throws IOException {

		// Creates new server object and runs it
		Server newSer = new Server();
		newSer.run();
	}
}