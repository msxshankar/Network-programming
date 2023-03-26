// Required imports
import java.io.*;
import java.net.*;

/**
 * Client class
 * Connects to server using ports
 * Sends command line arguments and receives output from server
 * @author Mayur Shankar
 * @since 27/03/2023
 */
public class Client {

	// Error codes
	private static final int failErrorCode = 1;
	private static final int successErrorCode = 0;

	// Variable declarations
	private static final int listeningPort = 6500;
	private Socket soc = null;
	private PrintWriter socketOut = null;
	private BufferedReader socketIn = null;

	/**
	 * Connects to port as localhost
	 * Exceptions are caught and handled
	 * @author Mayur Shankar
	 * @since 27/03/2023
	 */
	public void connect () {
		try {

			soc = new Socket("localhost", listeningPort);

			socketIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			socketOut = new PrintWriter(soc.getOutputStream(), true);

		}

		catch (UnknownHostException error) {
			System.err.println("Couldn't connect to server: Unknown Host Exception");
			System.exit(failErrorCode);
		}

		catch (IOException error) {
			System.err.println("Couldn't connect to IO stream: IO Exception error");
			System.exit(failErrorCode);
		}
	}

	/**
	 * Performs validation checks on command line arguments
	 * Then sends command line arguments to server and receives output
 	 * @param args - command line arguments
	 * @author Mayur Shankar
	 * @since 27/03/2023
	 */
	public void run (String[] args) {
		try {

			// No arguments are passed
			if (args.length == 0) {
				System.out.println("Usage: java Client [show] [item] [bid]");
				cleanup();
				System.exit(failErrorCode);
			}

			// Incorrect number of arguments passed
			if (args.length > 3) {
				System.out.println("Incorrect number of arguments passed");
				System.out.println("Usage: java Client [show] [item] [bid]");
				cleanup();
				System.exit(failErrorCode);
			}

			// Sends command line arguments to server as string
			String sendMessage = "";
			for (String arg : args) {
				sendMessage = sendMessage.concat(arg + " ");
			}
			socketOut.println(sendMessage);

			// Receives server message
			String input;
			while ((input = socketIn.readLine()) != null) {
				System.out.println(input);
			}
		}
		catch (IOException error) {
			System.err.println("Could not send info to server");
			cleanup();
		}
	}

	/**
	 * Closes socket connections
	 * @author Mayur Shankar
	 * @since 27/03/2023
 	 */
	public void cleanup () {
		try {
			socketIn.close();
			socketOut.close();
			soc.close();
		}
		catch (IOException error) {
			System.err.println("Unable to close socket connections: IO Exception Error");
		}

	}

	/**
	 * Runs Client program
 	 * @param args - command line arguments
	 * @author Mayur Shankar
	 * @since 27/03/2023
	 */
	public static void main( String[] args ) {

		// Creates new object and connects to port
		Client client = new Client();
		client.connect();

		// Communicates with server
		client.run(args);
	}
}