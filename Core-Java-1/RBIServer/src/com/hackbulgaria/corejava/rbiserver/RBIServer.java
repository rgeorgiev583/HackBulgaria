package com.hackbulgaria.corejava.rbiserver;

import java.net.Socket;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hackbulgaria.corejava.rbiprotocol.RBIConstants;
import com.hackbulgaria.corejava.rbiprotocol.RBIUtils;

public class RBIServer {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) {
		try (
			final ServerSocket socket = new ServerSocket(RBIConstants.PORT);
			final Socket clientSocket = socket.accept();
		) {
			String command;
			
			while (!(command = RBIUtils.readFrom(clientSocket)).equals(RBIConstants.QUIT_COMMAND) &&
				   !command.equals("")) {
				System.out.printf("Incoming client request to execute the `%s' command.\n", command.split("\\s"));
				System.out.printf("Started execution at %s ...\n", dateFormat.format(new Date()));
				final Process process = Runtime.getRuntime().exec(command);
				process.waitFor();
				System.out.printf("Finished execution at %s .\n", dateFormat.format(new Date()));
				final StringBuilder output = new StringBuilder();
				final BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String outputLine;
				
				while ((outputLine = outputReader.readLine()) != null) {
					output.append(outputLine);
					output.append("\n");
				}
				
				RBIUtils.writeTo(output.toString(), clientSocket);
				outputReader.close();
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
