package com.hackbulgaria.corejava.pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hackbulgaria.corejava.box.Box;

public class Pair<F, S> {
	private F first;
	private S second;

	public Pair() {
	}
	
	public Pair(F first, S second) {
		set(first, second);
	}

	public void set(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}
	
	public static void main(String[] args) {
		List<Pair<Integer, String> > listPairIntString = new ArrayList<Pair<Integer, String> >();
		listPairIntString.add(new Pair<>(2, "your"));
		listPairIntString.add(new Pair<>(1, "All"));
		listPairIntString.add(new Pair<>(3, "base"));
		listPairIntString.add(new Pair<>(5, "belong"));
		listPairIntString.add(new Pair<>(4, "are"));
		listPairIntString.add(new Pair<>(6, "to"));
		listPairIntString.add(new Pair<>(7, "us"));
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		for (Pair<Integer, String> pair : listPairIntString) {
			map.put(pair.getFirst(), pair.getSecond());
		}
		
		for (int i = 1; i <= 7; i++) {
			System.out.printf("%s ", map.get(i));
		}
	}
}
