package com.hackbulgaria.corejava.week0.kthmin;

import java.util.Arrays;

public class kthMin
{
    public static int kthMin(int k, int[] array)
    {
        int[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy);
    	
    	if (k <= arrayCopy.length) {
    		return arrayCopy[k - 1];
    	}
    	else {
    		return arrayCopy[arrayCopy.length - 1];
    	}
    }
    
    public static int kthMinAlt(int k, int[] array)
    {
        int mins[] = new int[k];

        for (int i = 0; i < k; i++) {
        	int j = 0;
        	boolean isNotPrevMinimum = false;
        	
        	while (j < array.length && !isNotPrevMinimum) {
        		int l = 0;
        		
        		while (l < i && array[j] != mins[l])
        			l++;
        		
        		isNotPrevMinimum = l == i;
        		j++;
            }
        	
        	if (j < array.length) {
        		mins[i] = array[j];
        	}

            while (j < array.length) {
        		int l = 0;
        		
        		while (l < i && array[j] != mins[l])
        			l++;

                if (l == i && array[j] > mins[i]) {
                	mins[i] = array[j];
                }
                
                j++;
            }
        }
        
        return mins[k - 1];
    }
    
    public static int kthMinAlt2(int k, int[] array)
    {
        int mins[] = new int[k];
        int minIndexes[] = new int[k];
        
        for (int i = 0; i < k; i++) {
        	int j = 0;
        	boolean isNotPrevMinimum = false;
        	
        	while (j < array.length && !isNotPrevMinimum) {
        		int l = 0;
        		
        		while (l < i && j != minIndexes[l])
        			l++;
        		
        		isNotPrevMinimum = l == i;
        		j++;
            }
        	
        	if (j < array.length) {
        		mins[i] = array[j];
        		minIndexes[i] = j;
        	}
            
            while (j < array.length) {
        		int l = 0;
        		
        		while (l < i && j != minIndexes[l])
        			l++;

                if (l == i && array[j] > mins[i]) {
                	mins[i] = array[j];
                	minIndexes[i] = j;
                }
                
                j++;
            }
        }
        
        return mins[k - 1];
    }
    
    public static void main(String[] args)
    {
        int[] array = {7, 5, 9, 3, 6, 1};
        System.out.println(kthMin(3, array));
        System.out.println(kthMinAlt(3, array));
        System.out.println(kthMinAlt2(3, array));
    }
}
