package com.inteliment.springrest.model;

import java.util.Map;

/**
 * <h1>Counter PoJo Class</h1>
 * This is the Pojo Class for Counter. It contains Map with words and their count in given paragraph.
 * @author  Dhaval Thakkar
 * @version 1.0
 */

public class Counter {

	private Map<String,Integer> counters;

	public Counter(){
	}

	public Counter(Map<String,Integer> counters){
		this.counters = counters;
	}

	/**
	 * Getter method returns the Counter details which contains word and their count in paragraph
	 * @return Map<String,Integer> This returns the object of Map for word and their count
	 */
	public Map<String,Integer> getCounters() {
		return counters;
	}

	/**
	 * Setter method to set Map of counter details which contains word and their count in paragraph
	 * @return Nothing
	 */
	public void setCounters(Map<String,Integer> counters) {
		this.counters = counters;
	}
}
