package edu.gatech.cs2340.thenullterminators.wheresmystuff;

import java.util.Comparator;

/**
 * A item comparator to be used with Collections.sort and does a sort specific
 * to items.
 * 
 * @author theNullTerminators
 * 
 */
public class ItemComparator implements Comparator<Item> {
	public int compare(Item c1, Item c2) {
		return c1.compareTo(c2);
	}
}
