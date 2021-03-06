package com.hackbulgaria.corejava.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
	@XmlAttribute
	private int studentId;
	@XmlAttribute
	private String name;
	
	private Student() {
	}
	
	public Student(int studentId, String name) {
		this.studentId = studentId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + "]";
	}
}
