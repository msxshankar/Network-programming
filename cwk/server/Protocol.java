// Required imports
import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;

/**
 * Protocol class
 * Contains methods to parse command line arguments
 * Performs relevant commands for show, item and bid
 * @author Mayur Shankar
 * @since 27/03/2023
 */
public class Protocol {

    // Error codes
    private static final int failErrorCode = 1;
    private static final int successErrorCode = 0;

    // Variable declarations
    public String returnMessage = "";
    public String clientIPAddress = "";
    private String[] cmdLineArguments;
    private FileWriter fptr = null;

    // Class data structure to hold data about every item
    public class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }

    // Creates an array of classes for all items
    public static ArrayList<Data> dataArray = new ArrayList<Data>();

    /**
     * Protocol Constructor
     * @param input - Sets cmdLineArguments to this value
     * @param clientAddress - Sets clientIPAddress to this value
     * @author Mayur Shankar
     * @since 27/03/2023
     */
    public Protocol (String[] input, String clientAddress) {
       cmdLineArguments = input;
       clientIPAddress = clientAddress;
    }

    /**
     * Parses command line arguments
     * Manipulates dataArray data structure
     * @author Mayur Shankar
     * @since 27/03/2023
     */
    public void run() {

      returnMessage = "";

      // Show command
      if (cmdLineArguments[0].equals("show")) {
          if (dataArray.size() == 0) {
              returnMessage = "There are currently no items in this auction";
          }

          // Creates string of all items in dataArray
          else {
              if (returnMessage.equals("")) {
                  for (int i = 0; i < dataArray.size(); i++) {
                     returnMessage = returnMessage.concat(dataArray.get(i).itemName + "  :  " + dataArray.get(i).currentBid + "  :  " + dataArray.get(i).clientAddress);

                     if (i+1 < dataArray.size()) {
                         returnMessage = returnMessage.concat("\n");
                     }
                  }
              }
          }

          // Logging
          log();
      }

      // Item command
      else if (cmdLineArguments[0].equals("item")) {

          // Check if an item already exists
          for (int i = 0; i < dataArray.size(); i++) {
              if (dataArray.get(i).itemName.equals(cmdLineArguments[1])) {
                  returnMessage = "Failure";
                  return;
              }
          }

          // Create new entry
          Data data = new Data();
          data.itemName = cmdLineArguments[1];
          data.currentBid = 0.0;
          data.clientAddress = "<no bids>";
          dataArray.add(data);

          returnMessage = "Accepted";
          log();

      }

      // Bid command
      else if (cmdLineArguments[0].equals("bid")) {

          if (cmdLineArguments.length < 3) {
              returnMessage = "Rejected";
              return;
          }

          // Converts price into double
          double price = Double.parseDouble(cmdLineArguments[2]);

          // Rejects prices less than or equal to 0
          if (price <= 0) {
              returnMessage = "Rejected";
          }

          // Check if bid price is greater than auction price
          for (int i = 0; i < dataArray.size() ; i++) {
              if (dataArray.get(i).itemName.equals(cmdLineArguments[1])) {
                  if (dataArray.get(i).currentBid >= price) {
                      returnMessage = "Rejected";
                  }
                  else {
                      returnMessage = "Accepted";
                      dataArray.get(i).currentBid = price;
                      dataArray.get(i).clientAddress = clientIPAddress;
                      log();
                  }
              }
          }

          // Bid made on item not in auction so reject
          if (returnMessage.equals("")) {
              returnMessage = "Rejected";
          }
      }
    }

    /**
     * Logs date, time, IP address and request to log.txt
     * @author Mayur Shankar
     * @since 27/03/2023
     */
    public void log () {
        try {
            // Date format
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            // Time format
            String time = LocalTime.now().toString();

            // Concatenate request
            String clientRequest = "";
            for (String s: cmdLineArguments) {
                clientRequest = clientRequest.concat(s + " ");
            }

            // Creates new file and append to it
            fptr = new FileWriter("log.txt", true);
            fptr.write(dateFormat.format(date) + " | " + time + " | " + clientIPAddress + " | " + clientRequest + "\n");
            fptr.close();

        }
        catch (IOException e) {
            System.err.println("Could not create/append to log.txt: IO Exception Error");
            System.exit(failErrorCode);
        }
    }
}
