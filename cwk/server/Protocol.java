// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {

    public String message = "";
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

    public void item (String item) {

        // Create new entry
        Data data = new Data();
        data.itemName = item;
        data.currentBid = 0.0;
        data.clientAddress = "127.0.0.0";
        dataArray.add(data);

        message = "Accepted";
    }

    public void bid (String item, String value) {
        double price = Double.parseDouble(value);

        if (price <= 0) {
            message = "Rejected";
        }

        for (int i = 0; i < dataArray.size() ; i++) {
            if (dataArray.get(i).itemName.equals(item)) {
                if (dataArray.get(i).currentBid >= Double.parseDouble(value)) {
                    message = "Rejected";
                }
                else {
                    message = "Accepted";
                }
            }
        }
        if (message.equals("")) {
            message = "Rejected";
        }
    }
}
