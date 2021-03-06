package com.hackbulgaria.corejava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ASCIIArtViewer {
	protected static final String newline = System.getProperty("line.separator");
	private BufferedImage image;
	
	public ASCIIArtViewer(String path) {
		File file = new File(path);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ASCIIArtViewer(File file) {		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public char getASCIIPixel(int x, int y, int blockWidth, int blockHeight) {
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

	public char getASCIIPixel(int x, int y) {
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
	
	public String getASCIIArt() {
		int width = image.getWidth(), height = image.getHeight();
		String art = "";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				art += getASCIIPixel(j, i);
			}
			
			art += newline;
		}
		
		return art;
	}
		
	public String getASCIIArt(int maxCols) {
		int width = image.getWidth(), height = image.getHeight();
		int blockWidth = width / maxCols, blockHeight = height / maxCols;
		String art = "";
		
		for (int i = 0; i < height; i += blockHeight) {
			for (int j = 0; j < width; j += blockWidth) {
				art += getASCIIPixel(j, i, j + blockWidth < width ? blockWidth : width - j, i + blockHeight < height ? blockHeight : height - i);
			}
			
			art += newline;
		}
		
		return art;
	}
	
	public void writeASCIIArtToFile(File file)
	{
		BufferedWriter writer = null;
		int width = image.getWidth(), height = image.getHeight();
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String line = "";
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					line += getASCIIPixel(j, i);
				}
				
				writer.write(line);
				writer.write(newline);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void writeASCIIArtToFile(File file, int maxCols)
	{
		BufferedWriter writer = null;
		int width = image.getWidth(), height = image.getHeight();
		System.out.println(image.getWidth());
		System.out.println(image.getHeight());
		int blockWidth = width / maxCols, blockHeight = height / maxCols;
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String line = "";
			
			for (int i = 0; i < height; i += blockHeight) {
				for (int j = 0; j < width; j += blockWidth) {
					line += getASCIIPixel(j, i, j + blockWidth < width ? blockWidth : width - j, i + blockHeight < height ? blockHeight : height - i);
				}
				
				writer.write(line);
				writer.write(newline);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static void main(String[] args) {
		File ifile = new File(args[0]);
		String ifilename = ifile.getName();
		String ifiletitle = ifilename.substring(0, ifilename.lastIndexOf('.'));
		ASCIIArtViewer aav = new ASCIIArtViewer(ifile);
		String ofilepath = Paths.get(ifile.getParent(), ifiletitle + ".txt").toString();
		File ofile = new File(ofilepath);
		aav.writeASCIIArtToFile(ofile);
		//System.out.println(new ASCIIArtViewer(args[0]).getASCIIArt());
	}
}
