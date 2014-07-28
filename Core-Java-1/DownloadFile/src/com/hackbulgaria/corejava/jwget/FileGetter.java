package com.hackbulgaria.corejava.jwget;

import java.net.URL;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class FileGetter {
	private static final int CHUNK_SIZE = 2 << 16 - 1;
	
	public static void downloadFile(URL source, String destination) throws IOException {
		BufferedInputStream in = new BufferedInputStream(source.openStream());
		FileOutputStream out = new FileOutputStream(destination);
		byte[] dataChunk = new byte[CHUNK_SIZE];
		int count;
		
		while ((count = in.read(dataChunk, 0, CHUNK_SIZE)) != -1) {
			out.write(dataChunk, 0, count);
		}

		out.close();
		in.close();
	}
	
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) {
			System.err.println("ERROR: Wrong number of arguments specified.");
			return;
		}
		
		try {
			URL source = new URL(args[0]);
			String sourcePath = source.getPath();
			downloadFile(source, args.length == 2 ? args[1] : sourcePath.substring(sourcePath.lastIndexOf("/") + 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
