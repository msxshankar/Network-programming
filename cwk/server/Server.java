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
	private Protocol spp = new Protocol();

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
				/*
				System.out.println(input);
				input = in.readLine();
				System.out.println(input);
				*/

				if (input.equals("show")) {
					spp.show();

					System.out.println(spp.dataArray);
					out.println(spp.error);

				}

				else if (input.equals("item")) {

					input = in.readLine();
					spp.item(input);

					System.out.println(spp.dataArray);
					out.println(spp.dataArray);
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