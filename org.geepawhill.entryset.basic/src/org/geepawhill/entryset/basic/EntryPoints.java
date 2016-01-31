package org.geepawhill.entryset.basic;

import java.util.ArrayList;
import java.util.Iterator;

import org.geepawhill.model.EntryPoint;
import org.geepawhill.model.EntryPointSet;

public class EntryPoints implements EntryPointSet {

	static class MyEntry implements EntryPoint {

		private String name;
		public MyEntry(String name)
		{
			this.name = name;
			
		}
		@Override
		public String name() {
			return name;
		}
	}
	
	@Override
	public Iterator<EntryPoint> iterator() {
		
		ArrayList<EntryPoint> points = new ArrayList<EntryPoint>();
		points.add(new MyEntry("One"));
		points.add(new MyEntry("Two"));
		return points.iterator();
	}

}
