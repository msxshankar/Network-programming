// Imports
import java.io.*;
import java.net.*;

/**
 * Client class
 */
public class Client {

	// Error status
	private static final int fail = 1;
	private static final int success = 0;

	// Variables
	private final int listeningPort = 6100;
	private Socket soc = null;
	private PrintWriter socketOut = null;
	private BufferedReader socketIn = null;

	/**
	 * Parses command line arguments
 	 * @param args
	 */
	public void parseCmdLineArgs(String[] args) {

		// Incorrect number of arguments so exits
		if (args.length == 0 || args.length > 3) {
			System.err.println("Incorrect number of arguments");
			System.exit(fail);
		}

		else if (args[0].equals("show")) {
			show();
		}

		else if (args[0].equals("item")) {
			;
		}

		else if (args[0].equals("bid")) {
			;
		}

		else {
			System.err.println("Unknown argument");
			System.exit(fail);
		}

	}

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

	/**
	 * Displays all items in the auction
 	 */
	public void show () {
		try {
			socketOut.println("show");

			System.out.println(socketIn.readLine());
			String info = "hello!";
			//String info = stdIn.readLine();
			//System.out.println(info);
			//String fromServer;
			//String fromUser;

			//stdIn.close();
			soc.close();
		}
		catch (IOException error) {
			System.out.println(error);
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

		// Parses command line arguments and communicates with server
		client.parseCmdLineArgs(args);
		client.show();
	}
}