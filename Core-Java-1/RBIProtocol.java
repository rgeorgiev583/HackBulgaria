package thirdTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class RBIProtocol {

    /**
     * RBIContains.PORT RBIUtils
     * 
     * @throws IOException
     */

    public final static int PORT = 4444;
    public final static String EOM = "<[?!EOM!?]>";
    public static String readFrom(Socket s) throws IOException {

        final Scanner scanner = new Scanner(s.getInputStream());
        final StringBuffer result = new StringBuffer();
        StringBuffer readEOMChar = new StringBuffer();
        while (!readEOMChar.equals(EOM)) {
            result.append(readEOMChar);
            readEOMChar = new StringBuffer();
            for (int i = 0; i < EOM.length(); i++) {

                readEOMChar.append(scanner.next());
            }
        }
        scanner.close();
        return result.toString();
    }

    public static void writeTo(String msg, Socket s) throws IOException {
        final PrintStream output = (PrintStream) s.getOutputStream();
        output.print(msg);
        output.print("\n");
        output.print(EOM);
        output.flush();
    }

}
