package com.hackbulgaria.corejava.printallhosts;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class HostPrinter {
	public static boolean isHostAddress(String ip) {
		try {
			return InetAddress.getByName(ip).isReachable(50);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void printHosts(String mask) {
		//for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				String ip = String.format(mask, 0, j);
				
				if (isHostAddress(ip)) {
					System.out.println(ip);
				}
			}
		//}
	}
	
	public static void main(String[] args) {
		printHosts("192.168.%d.%d");
	}
}
