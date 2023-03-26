// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {


    private static final int fail = 1;
    private static final int success = 0;
    public String message = "";
    private int bidMade = 0;
    private String[] request;
    private FileWriter fptr = null;

    public class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }
    public static ArrayList<Data> dataArray = new ArrayList<Data>();

    public Protocol (String[] input) {
       request = input;
    }

    public void run() {

      System.out.println("this"+request[1]);
      message = "";

      if (request.equals("show")) {
          if (dataArray.size() == 0) {
              message = "There are currently no items in this auction";
          }

          else {
              if (message.equals("")) {
                  for (int i = 0; i < dataArray.size(); i++) {
                     message = message.concat(dataArray.get(i).itemName + "  :  " + dataArray.get(i).currentBid + "  :  " + dataArray.get(i).clientAddress + "\n");
                  }
              }
              else {
                 ;
              }
          }
      }

      else if (request.equals("item")) {

          //InetSocketAddress sockAddress = (InetSocketAddress) clientSock.getRemoteSocketAddress();
          //String address = sockAddress.getAddress().getHostAddress().toString();

          // Check if an item already exists
          for (int i = 0; i < dataArray.size(); i++) {
              if (dataArray.get(i).itemName.equals(request[2])) {
                  message = "Failure";
              }
          }

          // Check whether bids have been made
          // Create new entry
          Data data = new Data();
          data.itemName = request[2];
          data.currentBid = 0.0;
          data.clientAddress = "<no bids>";
          dataArray.add(data);

          message = "Accepted";

      }

      else if (request.equals("bid")) {
          double price = Double.parseDouble(request[3]);

          if (price <= 0) {
              message = "Rejected";
          }

          for (int i = 0; i < dataArray.size() ; i++) {
              if (dataArray.get(i).itemName.equals(request[2])) {
                  if (dataArray.get(i).currentBid >= price) {
                      message = "Rejected";
                  }
                  else {
                      message = "Accepted";
                      dataArray.get(i).currentBid = price;
                  }
              }
          }
          if (message.equals("")) {
              message = "Rejected";
          }
      }

    }

    public void log () {
        try {
            fptr = new FileWriter("log.txt", true);
            fptr.write("date" + " | " + "time" + " | " + "address" + " | " + request[0]);
            fptr.close();

        }
        catch (IOException e) {
            System.err.println("Could not create log.txt");
        }
    }
}
