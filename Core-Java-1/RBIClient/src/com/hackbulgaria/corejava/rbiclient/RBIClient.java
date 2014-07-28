package com.hackbulgaria.corejava.rbiclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import com.hackbulgaria.corejava.rbiprotocol.RBIConstants;
import com.hackbulgaria.corejava.rbiprotocol.RBIUtils;

public class RBIClient {
    public static void main(String[] args) {
    	if (args.length != 1) {
    		System.err.println("Usage: java RBIClient <host name>");
    		System.err.println("Port number is fixed (4444).");
    	}
    	
        final String ip = args[0];
        
        try (
        	final Socket socket = new Socket(ip, RBIConstants.PORT);
        ) {
        	System.out.println(RBIConstants.CONNECTED_MESSAGE);
    		final BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        	String input = sysin.readLine();
        	
        	while (input != null && !input.equals(RBIConstants.QUIT_COMMAND)) {
        		RBIUtils.writeTo(input, socket);
        		input = sysin.readLine();
        		System.out.print(RBIUtils.readFrom(socket));
        	}
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
