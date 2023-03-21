// Imports
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Protocol class
 */
public class Protocol {

    class Data {
        String itemName;
        double currentBid;
        String clientAddress;
    }

    private ArrayList<Data> dataArray = new ArrayList<Data>();
    public String show () {

        Data data = new Data();
        data.itemName = "Table";
        data.currentBid = 10.1;
        data.clientAddress = "127.0.0.0";

        dataArray.add(data);

        if (dataArray.size() == 0) {
            return "There are currently no items in this auction";
        }

        else {
            for (int i = 0; i < dataArray.size(); i++) {
                System.out.println(dataArray.get(i));
            }
            return dataArray.get(0).clientAddress;
        }
    }
}
