// Required imports
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * Server class
 * Accepts client connection and assigns it to a new thread
 * @author Mayur Shankar
 * @since 27/03/2023
 */
public class Server {

	// Error codes
	private static final int failErrorCode = 1;
	private static final int successErrorCode = 0;

	// Variable declarations
	private static final int listeningPort = 6500;
	private ServerSocket serverSocket = null;
	ExecutorService service = null;

	/**
	 * Server Constructor which creates a server socket
	 * @author Mayur Shankar
	 * @since 27/03/2023
 	 */
	public Server() {
		try {
			serverSocket = new ServerSocket(listeningPort);
		}
		catch (IOException error) {
			System.err.println("Server could not listen on port: " + listeningPort);
			System.err.println("IO Exception Error");
		}
	}

	/**
	 * Creates a fixed thread pool with 30 threads
	 * Submits to Client Handler
	 * @author Mayur Shankar
	 * @since 27/03/2023
	 */
	public void run () {

		Socket clientSock = null;
		service = Executors.newFixedThreadPool(30);

		while (true) {

			try {
				clientSock = serverSocket.accept();
				service.submit(new ClientHandler(clientSock));

			} catch (IOException error) {
				System.err.println("Unable to submit client to thread: IO Exception Error");
			}
		}
	}

	/**
	 * Server program start
	 * @author Mayur Shankar
	 * @since 27/03/2023
	 */
	public static void main(String[] args) {

		// Creates new server object and runs it
		Server newSer = new Server();
		newSer.run();
	}
}