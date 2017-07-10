package com.inteliment.springrest.service;


import com.inteliment.springrest.model.Counter;
import com.inteliment.springrest.model.SearchText;

/**
 * Interface is generic for counter service
 * @author Dhaval Thakkar
 *
 */
public interface CounterService {
	public Counter getCounter();
	public Counter getCounter(SearchText searchText);
	public Counter getTopList(long top);
}
