import java.net.*;

public class Exercise {
	public static void main(String[] args) {

		// New object
		Exercise hostname = new Exercise();

		// One hostname
		if (args.length == 1) {
			hostname.oneHostname(args[0]);
		}
		// Two hostnames
		else if (args.length == 2) {
			hostname.twoHostname(args[0], args[1]);
		}
		// None or more than 2 hostnames
		else {
			System.out.println("Incorrect number of hostnames");
			System.exit(1);
		}
	}

	public void oneHostname(String argument) {
		try {
			// Print hostname and IP address
			InetAddress ip = InetAddress.getByName(argument);
			System.out.println("Host Name: " + ip.getHostName());
			System.out.println("IP Address: " + ip.getHostAddress());

			// Determine if IPV4 or IPV6
			if (ip instanceof Inet6Address) {
				System.out.println("IP is a IPV6 Address");
			} else if (ip instanceof Inet4Address) {
				System.out.println("IP is a IPV4 Address");
			} else {
				System.out.println("Unknown type");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String twoHostname(String argument1, String argument2) {
		try {
			InetAddress ip1 = InetAddress.getByName(argument1);
			InetAddress ip2 = InetAddress.getByName(argument2);

			// Check if IPv4
			if (!(ip1 instanceof Inet4Address && ip2 instanceof Inet4Address)) {
				System.out.println("Not a valid IPv4 Address");
				System.exit(2);
			}


			// Convert to string
			String[] address1 = ip1.getHostAddress().toString().split("\\.");
			String[] address2 = ip2.getHostAddress().toString().split("\\.");
			//StringBuilder outputAddress = new StringBuilder();

			// Make sure addresses are the same length
			if (ip1.getAddress().length != ip2.getAddress().length) {
				System.out.println("Invalid IPv4 Address");
				System.exit(3);
			}

			// Detect hierarchy
			for (int i = 0; i < ip1.getAddress().length; i++) {
				if (!(address1[i].equals(address2[i]))) {

					// If addresses don't match at all
					if (i == 0) {
						System.out.println("*.*.*.*");
						System.exit(0);
					}

					else {
						for (int k = i; k < ip1.getAddress().length; k++) {
							address1[k] = "*";
						}
						break;
					}
				}
			}

			// Output each index of address
			for (int j = 0; j < ip1.getAddress().length; j++) {
				System.out.print(address1[j] + ((j == ip1.getAddress().length) ? "" : "."));
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}
}
