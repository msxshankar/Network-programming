// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * ClientHandler class
 */
public class ClientHandler extends Thread {
   private Socket socket = null;
   private Protocol spp = null;
   private FileWriter fptr = null;

   public ClientHandler (Socket socket) {
       super("ClientHandler");
       this.socket = socket;
   }

   public void run () {

       try {
           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));

           String[] input = in.readLine().split(" ");
           System.out.println(input[0]);

           InetSocketAddress sockAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
           String address = sockAddress.getAddress().getHostAddress().toString();

           spp = new Protocol(input, address);
           spp.run();

           out.println(spp.message);

           out.close();
           in.close();
           socket.close();
       }
       catch (IOException error) {
          System.err.println("Client Handler Error");
       }
   }
}
