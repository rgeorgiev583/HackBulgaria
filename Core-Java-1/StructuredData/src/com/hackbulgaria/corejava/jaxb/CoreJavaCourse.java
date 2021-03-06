package com.hackbulgaria.corejava.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CoreJavaCourse {
	@XmlElement(name = "student")
	private List<Student> enrolledStudents = new ArrayList<Student>();
	@XmlAttribute
	private int courseId;
	@XmlAttribute
	private String courseName;
	
	public CoreJavaCourse() {
	}
	
	public CoreJavaCourse(int courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public void addStudent(Student student) {
		enrolledStudents.add(student);
	}

	@Override
	public String toString() {
		return "CoreJavaCourse [enrolledStudents=" + enrolledStudents
				+ ", courseId=" + courseId + ", courseName=" + courseName + "]";
	}
}
