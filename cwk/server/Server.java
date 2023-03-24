// Imports
import java.net.*;
import java.io.*;

/**
 * Server class
 */
public class Server {

	private static final int fail = 1;
	private static final int success = 0;

	private int listeningPort = 6500;
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

		Socket clientSock = null;
		while (true) {

			try {
				clientSock = serverSocket.accept();
			}
			catch (IOException error) {
				System.err.println("could not listen");
				System.exit(fail);
			}

			try {
				PrintWriter out = new PrintWriter(clientSock.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

				String input;
				input = in.readLine();

				if (input.equals("show")) {
					spp = new Protocol();
					spp.show();

					out.println(spp.error);

				}

				else if (input.equals("itemtable")) {
					spp = new Protocol();
					spp.item();
					out.println(spp.dataArray.get(0).itemName);
				}
				out.close();
				in.close();
				clientSock.close();

			} catch (IOException error) {
				System.err.println("Could not accept connection: " + listeningPort);
				System.exit(fail);
			}
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