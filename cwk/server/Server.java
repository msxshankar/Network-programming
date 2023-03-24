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

		try {
			fptr = new FileWriter("log.txt", true);
		}
		catch (IOException e) {
			System.err.println("Could not create log.txt");
		}

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

				spp.message = "";
				String input;
				input = in.readLine();

				fptr.write(input);

				if (input.equals("show")) {
					spp.show();
					System.out.println(spp.message);
					if (spp.message.equals("")) {
						for (int i = 0; i < spp.dataArray.size(); i++) {
							out.println(spp.dataArray.get(i).itemName + " : " + spp.dataArray.get(i).currentBid + " : " + spp.dataArray.get(i).clientAddress);
						}
					}
					else {
						out.println(spp.message);
					}
				}

				else if (input.equals("item")) {

					input = in.readLine();
					spp.item(input);

					out.println(spp.message);
				}

				else if (input.equals("bid")) {
					String item = in.readLine();
					String value = in.readLine();
					spp.bid(item, value);
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