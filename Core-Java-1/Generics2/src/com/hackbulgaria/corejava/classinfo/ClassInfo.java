package com.hackbulgaria.corejava.classinfo;

public @interface ClassInfo {
	String author();
	int currentRevision() default 1;
	boolean isChecked() default false;
	Class<?>[] relatedClasses();
}
