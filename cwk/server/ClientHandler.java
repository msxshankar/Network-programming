// Required imports
import java.net.*;
import java.io.*;

/**
 * ClientHandler class which extends Thread class
 * Uses Protocol class to parse command line arguments
 * Returns correct information to client
 * @author Mayur Shankar
 * @since 27/03/2023
 */
public class ClientHandler extends Thread {

   // Error code
   private static final int failErrorCode = 0;

   // Variable declarations
   private Socket socket = null;
   private Protocol protocol = null;
   private FileWriter fptr = null;

    /**
     * Client Handler Constructor
     * Allows client to be run on a thread from the thread pool
     * @param socket - client socket
     */
   public ClientHandler (Socket socket) {
       super("ClientHandler");
       this.socket = socket;
   }

    /**
     * Reads information sent from client
     * Uses Protocol class to work on information
     * Sends information back to client
     * @author Mayur Shankar
     * @since 27/03/2023
     */
   public void run () {

       try {
           // Socket streams
           PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
           BufferedReader socketIn = new BufferedReader (new InputStreamReader(socket.getInputStream()));

           // Read from client
           String[] clientInput = socketIn.readLine().split(" ");

           // Gets client's IP Address
           InetSocketAddress sockAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
           String clientAddress = sockAddress.getAddress().getHostAddress().toString();

           // Creates Protocol object and runs method
           protocol = new Protocol(clientInput, clientAddress);
           protocol.run();

           // Sends message back to client
           socketOut.println(protocol.returnMessage);

           // Closes socket streams
           socketOut.close();
           socketIn.close();
           socket.close();
       }
       catch (IOException error) {
          System.err.println("Could read from / write to client: IO Exception Error");
          System.exit(failErrorCode);
       }
   }
}
