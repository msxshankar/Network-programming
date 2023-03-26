// Imports
import java.io.*;
import java.net.*;

/**
 * Client class
 */
public class Client extends Thread {

	// Error status
	private static final int fail = 1;
	private static final int success = 0;

	// Variables
	private final int listeningPort = 6500;
	private Socket soc = null;
	private PrintWriter socketOut = null;
	private BufferedReader socketIn = null;

	/**
	 * Connects to port and returns error if unsuccessful
	 */
	public void connect () {
		try {

			soc = new Socket("localhost", listeningPort);

			socketIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			socketOut = new PrintWriter(soc.getOutputStream(), true);

		}

		catch (UnknownHostException error) {
			System.err.println("Unknown host exception");
			System.exit(fail);
		}

		catch (IOException error) {
			System.err.println("Couldn't get I/O for the connection to host");
			System.exit(fail);
		}
	}

	public void run (String[] args) {
		try {
			/*
			for (String s: args) {
				socketOut.println(s);
			}
			*/

			socketOut.println(args[0]);

			System.out.println("client here");
			/*
			String input;
			while ((input = socketIn.readLine()) != null) {
				System.out.println(input);
			}
			 */
			String input = socketIn.readLine();
			System.out.println(input);

			socketIn.close();
			socketOut.close();
			soc.close();
		}
		catch (IOException error) {
			System.err.println("Could not send info to server");
		}
	}

	public void cleanup () {
		try {
			socketIn.close();
			socketOut.close();
			soc.close();
		}
		catch (IOException error) {
			System.out.println("Could not cleanup");
		}
	}

	/**
	 * Start of client program
 	 * @param args
	 */
	public static void main( String[] args ) {

		// Creates new object and connects to port
		Client client = new Client();
		client.connect();

		// Communicates with server
		client.run(args);
	}
}