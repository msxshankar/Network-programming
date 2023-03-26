// Imports
import java.net.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {


    private static final int fail = 1;
    private static final int success = 0;
    public String message = "";
    public String address = "";
    private int bidMade = 0;
    private String[] request;
    private FileWriter fptr = null;

    public class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }
    public static ArrayList<Data> dataArray = new ArrayList<Data>();

    public Protocol (String[] input, String clientAddress) {
       request = input;
       address = clientAddress;
    }

    public void run() {

      message = "";

      if (request[0].equals("show")) {
          if (dataArray.size() == 0) {
              message = "There are currently no items in this auction";
          }

          else {
              if (message.equals("")) {
                  for (int i = 0; i < dataArray.size(); i++) {
                     message = message.concat(dataArray.get(i).itemName + "  :  " + dataArray.get(i).currentBid + "  :  " + dataArray.get(i).clientAddress);

                     if (i+1 < dataArray.size()) {
                         message = message.concat("\n");
                     }
                  }
              }
              else {
                 ;
              }
          }

          log();
      }

      else if (request[0].equals("item")) {

          //InetSocketAddress sockAddress = (InetSocketAddress) clientSock.getRemoteSocketAddress();
          //String address = sockAddress.getAddress().getHostAddress().toString();

          // Check if an item already exists
          for (int i = 0; i < dataArray.size(); i++) {
              if (dataArray.get(i).itemName.equals(request[1])) {
                  message = "Failure";
                  return;
              }
          }

          // Check whether bids have been made
          // Create new entry
          Data data = new Data();
          data.itemName = request[1];
          data.currentBid = 0.0;
          data.clientAddress = "<no bids>";
          dataArray.add(data);

          message = "Accepted";
          log();

      }

      else if (request[0].equals("bid")) {

          if (request.length < 3) {
              message = "Rejected";
              return;
          }

          double price = Double.parseDouble(request[2]);

          if (price <= 0) {
              message = "Rejected";
          }

          for (int i = 0; i < dataArray.size() ; i++) {
              if (dataArray.get(i).itemName.equals(request[1])) {
                  if (dataArray.get(i).currentBid >= price) {
                      message = "Rejected";
                  }
                  else {
                      message = "Accepted";
                      dataArray.get(i).currentBid = price;
                      dataArray.get(i).clientAddress = address;
                  }
              }
          }
          if (message.equals("")) {
              message = "Rejected";
          }

          log();
      }

    }

    public void log () {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            fptr = new FileWriter("log.txt", true);
            fptr.write(dateFormat.format(date) + " | " + Instant.now().toString() + " | " + address + " | " + request[0] + "\n");
            fptr.close();

        }
        catch (IOException e) {
            System.err.println("Could not create log.txt");
        }
    }
}
