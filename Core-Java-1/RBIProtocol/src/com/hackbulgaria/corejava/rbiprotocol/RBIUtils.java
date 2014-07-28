package com.hackbulgaria.corejava.rbiprotocol;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class RBIUtils {
	public static String readFrom(Socket s) throws IOException {
		final BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {// && !inputLine.equals(RBIConstants.EOM)) {
			sb.append(inputLine);
			sb.append("\n");
		}

		in.close();
		return sb.toString();
	}

	public static void writeTo(String message, Socket s) throws IOException {
		final PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println(message);
		out.print(RBIConstants.EOM);
		out.flush();
	}
}
