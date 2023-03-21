// Imports
import java.net.*;
import java.io.*;

/**
 * Server class
 */
public class Server {

	private static final int fail = 1;
	private static final int success = 0;

	private int listeningPort = 6100;
	private ServerSocket serverSocket = null;
	private Protocol spp = null;

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
		try {
			Socket clientSock = serverSocket.accept();


			PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

			//System.out.println(in.readLine());

			spp = new Protocol();
			String newData = spp.show();
			//writer.print(newData);

			writer.close();
			clientSock.close();
		}
		catch (IOException error) {
			System.err.println("Could not accept connection: "+listeningPort);
			System.exit(fail);
		}
	}

	/**
	 * Server program start
	 * @param args
	 */
	public static void main( String[] args ) {

		// Creates new server object and runs it
		Server newSer = new Server();
		newSer.run();
	}
}