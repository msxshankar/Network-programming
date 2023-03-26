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
            /*
           String[] input = new String[3];
           for (int i = 0; i < 3; i++) {
               input[i] = in.readLine();
           }
            */
           //String input = in.toString();
           //String[] input = in.toString().split(" ");
           //System.out.println(input[0]);
           //spp = new Protocol(input);
           //spp.run();
           System.out.println("here");

           out.println("message");

           out.close();
           in.close();
           socket.close();
       }
       catch (IOException error) {
          System.err.println("Client Handler Error");
       }
   }
}
