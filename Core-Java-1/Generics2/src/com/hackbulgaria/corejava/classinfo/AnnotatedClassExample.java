package com.hackbulgaria.corejava.classinfo;

import java.lang.annotation.Annotation;

@ClassInfo (
	author = "Radoslav Georgiev",
	currentRevision = 1,
	isChecked = false,
	relatedClasses = {Object.class}
)
public class AnnotatedClassExample {
	public static void main(String[] args) {
		AnnotatedClassExample example = new AnnotatedClassExample();
		Annotation[] exampleAnnotations = example.getClass().getAnnotations();
		for (Annotation annotation : exampleAnnotations) {
			System.out.println(annotation.toString());
		}

		System.out.println(example.getClass().getAnnotation(ClassInfo.class));
	}
}
