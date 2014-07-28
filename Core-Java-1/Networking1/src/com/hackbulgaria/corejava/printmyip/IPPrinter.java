package com.hackbulgaria.corejava.printmyip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class IPPrinter {
	public static void printInetAddresses(NetworkInterface networkInterface) throws SocketException {
		Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
		
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			System.out.println(inetAddress.getHostAddress());
		}
	}
	
	public static void main(String[] args) {
		try {
			printInetAddresses(NetworkInterface.getByName("wlan0"));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
