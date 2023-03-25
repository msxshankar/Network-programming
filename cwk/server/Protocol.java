// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {

    public String message = "";
    private int bidMade = 0;
    public class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }
    public static ArrayList<Data> dataArray = new ArrayList<Data>();

    /*
    public Protocol () {
        dataArray = new ArrayList<Data>();
    }
    */

    public void show () {

        if (dataArray.size() == 0) {
           message = "There are currently no items in this auction";
        }

        else {
           ;
        }
    }

    public String item (String item, String address) {

        // Check if an item already exists
        for (int i = 0; i < dataArray.size(); i++) {
            if (dataArray.get(i).itemName.equals(item)) {
                return "Failure";
            }
        }

        // Check whether bids have been made
        // Create new entry
        Data data = new Data();
        data.itemName = item;
        data.currentBid = 0.0;
        data.clientAddress = "<no bids>";
        dataArray.add(data);

        message = "Accepted";

        return "Success";
    }

    public void bid (String item, String value) {
        double price = Double.parseDouble(value);

        if (price <= 0) {
            message = "Rejected";
        }

        for (int i = 0; i < dataArray.size() ; i++) {
            if (dataArray.get(i).itemName.equals(item)) {
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
