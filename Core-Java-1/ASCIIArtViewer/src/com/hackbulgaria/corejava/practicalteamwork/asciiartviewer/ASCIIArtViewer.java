package com.hackbulgaria.corejava.practicalteamwork.asciiartviewer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ASCIIArtViewer {
	//public static newline = System.getProperty("line.separator");
	BufferedImage image;
	
	public ASCIIArtViewer(String path) {
		File file = new File(path);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	char getASCIIPixel(int x, int y, int blockWidth, int blockHeight) {
		int intensity = 0;
		
		for (int i = 0; i < blockHeight; i++) {
			for (int j = 0; j < blockWidth; j++) {
				Color color = new Color(image.getRGB(x + j, y + i));
				intensity += (color.getRed() + color.getGreen() + color.getBlue()) / 3;
			}
		}
		
		intensity /= blockWidth * blockHeight;
		
		if (intensity > 240) {
			return ' ';
		}
		else if (intensity > 200 && intensity <= 240) {
			return '.';
		}
		else if (intensity > 160 && intensity <= 200) {
			return '*';
		}
		else if (intensity > 120 && intensity <= 160) {
			return 'x';
		}
		else if (intensity > 80 && intensity <= 120) {
			return '$';
		}
		else if (intensity > 40 && intensity <= 80) {
			return '#';
		}
		else {
			return '@';
		}
	}

	char getASCIIPixel(int x, int y) {
		Color color = new Color(image.getRGB(x, y));
		int intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
		
		if (intensity > 240) {
			return ' ';
		}
		else if (intensity > 200 && intensity <= 240) {
			return '.';
		}
		else if (intensity > 160 && intensity <= 200) {
			return '*';
		}
		else if (intensity > 120 && intensity <= 160) {
			return '+';
		}
		else if (intensity > 80 && intensity <= 120) {
			return 'x';
		}
		else if (intensity > 40 && intensity <= 80) {
			return '#';
		}
		else {
			return '@';
		}
	}
	
	String getASCIIArt() {
		int width = image.getWidth(), height = image.getHeight();
		int blockWidth = width / 80, blockHeight = height / 80;
		System.out.println(blockWidth);
		System.out.println(blockHeight);
		String art = "";
		
		for (int i = 0; i < height; i += blockHeight) {
			for (int j = 0; j < width; j += blockWidth) {
				art += getASCIIPixel(j, i, blockWidth, blockHeight);
			}
			
			art += "\n";
		}
		
		return art;
	}
	
	public static void main(String[] args) {
		System.out.println(new ASCIIArtViewer(args[0]).getASCIIArt());
	}
}