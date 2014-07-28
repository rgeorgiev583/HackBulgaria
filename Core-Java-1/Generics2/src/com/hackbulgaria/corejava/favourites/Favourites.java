package com.hackbulgaria.corejava.favourites;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hackbulgaria.corejava.pair.Pair;

public class Favourites implements Iterable<Pair<Class<?>, Object> > {
	private Map<Class<?>, Object> map;
	
	public Favourites() {
		map = new HashMap<Class<?>, Object>();
	}
	
	public Object get(Class<?> cls) {
		return map.get(cls);
	}
	
	public void add(Class<?> cls, Object obj) {
		map.put(cls, obj);
	}

	@Override
	public Iterator<Pair<Class<?>, Object> > iterator() {
		return new Iterator<Pair<Class<?>, Object> >() {
			private final Iterator<Map.Entry<Class<?>, Object> > mapIterator = map.entrySet().iterator();

			@Override
			public boolean hasNext() {
				return mapIterator.hasNext();
			}

			@Override
			public Pair<Class<?>, Object> next() {
				Map.Entry<Class<?>, Object> entry = mapIterator.next();
				return new Pair<Class<?>, Object>(entry.getKey(), entry.getValue());
			}

			@Override
			public void remove() {
				mapIterator.remove();
			}
		};
	}
	
	public static void main(String[] args) {
		Favourites favourites = new Favourites();
		favourites.add(String.class, "myString");
		favourites.add(Integer.class, 3);

		System.out.println(favourites.get(Integer.class));
		System.out.println(favourites.get(String.class));
		
		Iterator<Pair<Class<?>, Object> > iterator = favourites.iterator();
		
		while (iterator.hasNext()) {
			Pair<Class<?>, Object> entry = iterator.next();
			System.out.printf("%s: %s\n", entry.getFirst().getCanonicalName(), entry.getSecond());
		}
	}

}
