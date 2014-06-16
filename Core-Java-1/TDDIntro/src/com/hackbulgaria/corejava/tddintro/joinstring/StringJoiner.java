package com.hackbulgaria.corejava.tddintro.joinstring;

public class StringJoiner {
    public String stitchMeUp(Object glue, Object... args) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]);
            sb.append(glue);
        }
        
        sb.append(args[args.length - 1]);
        return sb.toString();
    }
}
