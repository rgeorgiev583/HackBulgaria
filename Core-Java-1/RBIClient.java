package thirdTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class RBIClient {
    static String beginSearchMassage = "Connection successfull";
    static String quit;

    public static void main(String[] args) throws IOException {
        final String ip = args[0];
        final Socket socket = new Socket();
        boolean notConnected = false;
        final Date now = new Date();
        do {
            try {
                socket.connect(new InetSocketAddress(ip, RBIProtocol.PORT));
                notConnected = false;
            } catch (final Exception e) {
                notConnected = true;
            }
        } while (notConnected && now.before(new Date()));
        System.out.println(beginSearchMassage);
        final InputStream inputStream = System.in;
        final Scanner inputScanner = new Scanner(inputStream);
        String clientInput = inputScanner.nextLine();
        while (!quit.equals(clientInput)) {
            clientInput = inputScanner.nextLine();
            RBIProtocol.writeTo(clientInput, socket);
            System.out.println(RBIProtocol.readFrom(socket));
        }
        inputScanner.close();
    }

}
