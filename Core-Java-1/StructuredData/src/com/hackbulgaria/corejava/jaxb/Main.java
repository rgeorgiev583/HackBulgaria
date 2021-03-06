package com.hackbulgaria.corejava.jaxb;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONString;
import org.codehaus.jettison.json.JSONStringer;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

public class Main {
	public static void main(String[] args) throws JAXBException, IOException, JSONException, XMLStreamException {
		File xml_file = new File("/tmp/shit.xml");
		File json_file = new File("/tmp/shit.json");
		
		CoreJavaCourse cjc = new CoreJavaCourse(123, "Design Patterns with Java");
		cjc.addStudent(new Student(1, "Petar Petrov"));
		cjc.addStudent(new Student(0, "Alexander Alexandrov"));
		JAXBContext jaxbc = JAXBContext.newInstance(CoreJavaCourse.class, Student.class);
		Marshaller marshaller = jaxbc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(cjc, xml_file);
		
		Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
		CoreJavaCourse cjc1 = (CoreJavaCourse) unmarshaller.unmarshal(xml_file);
		System.out.println(cjc1.toString());
		
		MappedNamespaceConvention mnc = new MappedNamespaceConvention();
		MappedXMLStreamWriter mxsw = new MappedXMLStreamWriter(mnc, new FileWriter(json_file));
		marshaller.marshal(cjc, mxsw);
		
		JSONObject json = new JSONObject(new String(Files.readAllBytes(Paths.get("/tmp/shit.json"))));
		MappedXMLStreamReader mxsr = new MappedXMLStreamReader(json, mnc);
		CoreJavaCourse cjc2 = (CoreJavaCourse) unmarshaller.unmarshal(mxsr);
		System.out.println(cjc2.toString());
	}
}
