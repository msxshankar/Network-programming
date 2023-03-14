package client;

import java.io.*;
import java.net.*;
public class Client
{

	public void connect () {
		try {
			for (int i = 6000; i < 7000; i++) {
				Socket s = new Socket("localhost", i);
				System.out.println("There is a server on port " + i + " of " + "localhost");
			}

			/*
			BufferedReader reader = new BufferedReader (new InputStreamReader(s.getInputStream()));
			String advice = reader.readLine();
			System.out.println("Hello" + advice);
			reader.close();
			s.close();
			 */
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	public static void main( String[] args )
	{
		Client client = new Client();
		client.connect();
	}
}