// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {

    String error;
    public class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }
    public ArrayList<Data> dataArray = new ArrayList<Data>();

    /*
    public Protocol () {
        dataArray = new ArrayList<Data>();
    }
    */

    public void show () {

        if (dataArray.size() == 0) {
           error = "There are currently no items in this auction";
        }

        else {
           error = dataArray.get(0).itemName;
        }
    }

    public void item (String item) {

        // Create new entry
        Data data = new Data();
        data.itemName = item;
        data.currentBid = 0.0;
        data.clientAddress = "127.0.0.0";
        dataArray.add(data);
    }

    public void bid () {}
}
