package com.hackbulgaria.corejava.tddintro.reducefilepath;

public class FilePathReducer {
    public String reduceFilePathWithRegex(String path) {
        path = path.replaceAll("//+", "/");
        path = path.replaceAll("(?<=/)\\./", "");
        path = path.replaceAll("(?<=/)[^/]+(?<!/\\.)(?<!/\\.\\.)/\\.\\./", "");
        path = path.replaceAll("^\\./", "");
        path = path.replaceAll("(?<=/)\\.$", "");
        path = path.replaceAll("^\\.$", "");
        path = path.replaceAll("^[^/]+(?<!\\.)(?<!\\.\\.)/\\.\\./", "");
        path = path.replaceAll("(?<=/)[^/]+(?<!/\\.)(?<!/\\.\\.)/\\.\\.$", "");
        path = path.replaceAll("^[^/]+(?<!\\.)(?<!\\.\\.)/\\.\\.$", "");
        path = path.replaceAll("(?<=[^/])/$", "");
        path = path.replaceAll("^(?:/\\.\\.)+", "/");
        return path;
    }
}