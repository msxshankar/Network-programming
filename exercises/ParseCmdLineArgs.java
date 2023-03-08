//
// Simple Java code intended as a refresher for students who need to 'brush up' on their Java.
// There are no network features in this example.
//
// Compile with: javac ParseCmdLineArgs.java
// Execute with: java ParseCmdLineArgs arg1 arg2 arg3 ...
//

public class ParseCmdLineArgs {

	// The sole constructor, which expects the command line arguments to be provided as a String array.
	public ParseCmdLineArgs( String[] args )
	{
		// No arguments and exits
		if (args.length == 0) {
			System.out.println("No arguments");
			System.exit(0);
		}

		// Store argument in private string
		else {
			String[] cliArgs = args;
		}

		// Detect hostname and IPv4 address
		for (int i = 0;i < args.length; i++) {

			// Allows easy changes to strings
			StringBuilder argument = new StringBuilder(args[i]);

			// Detect hostname
			if (args[i].contains(".")) {
				System.out.print(args[i]);
				System.out.print(" may be a hostname");

				// Detect IPv4 address
				int count = 0;
				for (int j = 0; j < args[i].length(); j++) {
					int index = argument.indexOf(".");
					if (!(index == -1)) {
						argument.deleteCharAt(index);
						count++;
					}
					else {
						break;
					}
				}
				// If the string contains 3 dots
				if (count == 3) {
					System.out.println(" and may be an IPv4 address");
				}
			}
			System.out.println("\n");
		}
	}

    // main(): This is the function that is called after executing with 'java ParseCmdLineArgs'.
	// Any command line arguments are passed as the string array 'String[] args', i.e. if you execute the code with
	//   java ParseCmdLineArgs arg1 arg2 arg3
	// then String[] args will be an array of length 3, containing the strings 'arg1', arg2', and arg3.'
    public static void main( String[] args )
	{
		ParseCmdLineArgs parser = new ParseCmdLineArgs(args);
	}
}