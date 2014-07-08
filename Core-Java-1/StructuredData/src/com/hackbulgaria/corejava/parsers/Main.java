package com.hackbulgaria.corejava.parsers;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {
/*	public void printDocumentElement(Element e) {
		System.out.println("=== Element `" + e.getNodeName() + "'");
		System.out.println("Namespace: " + e.getNamespaceURI());
		System.out.println("Parent: " + e.getParentNode().getNodeName());
		System.out.println("Attributes:");
		NamedNodeMap attributes = e.getAttributes();
		for (Map.Entry<String, Attr> attr : attributes.entrySet()) {
			
		}
		System.out.println(" - " + );
	}*/
	
	public void runDOMParser() {
		//long startTime = System.nanoTime();
		File file = new File("/tmp/metawiki-latest-stub-articles.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = (Document) db.parse(file);
		//long endTime = System.nanoTime();
		System.out.println(doc.getDocumentElement().getNodeName());
		//doc.getDocumentElement().normalize();
	}
	
	public void runSAXParser() {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			DefaultHandler dh = new DefaultHandler() {
				String username;
				int id;
				
				public void startElement(String uri, String localName, )
			}
		}
		
	}
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

	}
}
