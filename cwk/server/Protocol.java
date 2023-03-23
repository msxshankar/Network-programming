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

    public void show () {

        if (dataArray.size() == 0) {
           error = "There are currently no items in this auction";
        }

        else {
           ;
        }
    }

    public void item () {

        // Create new entry
        Data data = new Data();
        data.itemName = "Table";
        data.currentBid = 10.1;
        data.clientAddress = "127.0.0.0";
        dataArray.add(data);
    }

    public void bid () {}
}
