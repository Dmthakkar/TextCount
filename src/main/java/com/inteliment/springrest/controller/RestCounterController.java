package com.inteliment.springrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inteliment.springrest.model.Counter;
import com.inteliment.springrest.model.SearchText;
import com.inteliment.springrest.service.CounterService;

/**
 * <h1>Rest Controller</h1>
 * This Rest Controller contains three major services
 * <p>
 * /counter-api/counterlist/ -- It will display list of all the word with their count
 * /counter-api/search/ -- It will provide the count of given words as input parameter
 * /counter-api/top/{TopNumber}* -- It will provide the Top N words with their count in descending order
 *
 * @author  Dhaval Thakkar
 * @version 1.0
 */

@RestController
public class RestCounterController {
	
	public static String CLASSNAME = "RestCounterController";
    private final Logger LOG = LoggerFactory.getLogger(RestCounterController.class);

	@Autowired
	private CounterService counterService;

	/**
	 * This service is used to display full list of words contains in paragraph.
	 * 
	 * @return ResponseEntiry<Counter> This returns the object of Counter which contains Map
	 */
	@RequestMapping(value = "/counterlist/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Counter> getCounter() {
		LOG.info(CLASSNAME + " getCounter() Called...");
		Counter counterText = counterService.getCounter();
		if(counterText == null || (counterText != null && counterText.getCounters().isEmpty())){
			LOG.info(CLASSNAME + "getCounter() Counters is Empty.");
			return new ResponseEntity<Counter>(HttpStatus.NO_CONTENT);
		}
//		LOG.info(CLASSNAME + "getCounter() Counter is :" + counterText.getCounters());
		return new ResponseEntity<Counter>(counterText, HttpStatus.OK);
	}	

	/**
	 * This service is used to display full list  of words contains in paragraph.
	 * 
	 * @param RequestBody This is search words provided to return count of specific words
	 * @return ResponseEntiry<Counter> This returns the object of Counter which contains Map
	 */
	@RequestMapping(value = "/search/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Counter> getCounter(@RequestBody SearchText searchText) {
		LOG.info(CLASSNAME + "getCounter(searchText) Calling...searchText is "+ searchText);
		Counter counterText = counterService.getCounter(searchText);
		if(counterText == null || (counterText != null && counterText.getCounters().isEmpty())){
			LOG.info(CLASSNAME + "getCountr(searchText) Counters is Empty.");
			return new ResponseEntity<Counter>(HttpStatus.NO_CONTENT);
		}
//		LOG.info(CLASSNAME + "getCounter(searchText) Counter is :" + counterText.getCounters());
		return new ResponseEntity<Counter>(counterText, HttpStatus.OK);
	}	

	/**
	 * This service is used to display full list of words contains in paragraph.
	 * 
	 * @param PathVariable This is Top N words which has the highest counts in the Sample Paragraphs provided.
	 * @return ResponseEntiry<Counter> This returns the object of Counter which contains Map
	 */
	@RequestMapping(value = "/top/{topn}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Counter> getCounter(@PathVariable("topn") long topn) {
		LOG.info(CLASSNAME + "getCounter(topn) Calling...topn is "+ topn);
		Counter counterText = counterService.getTopList(topn);
		if(counterText == null || (counterText != null && counterText.getCounters().isEmpty())){
			LOG.info(CLASSNAME + "getCountr(topn) Counters is Empty.");
			return new ResponseEntity<Counter>(HttpStatus.NO_CONTENT);
		}
//		LOG.info(CLASSNAME + "getCounter(topn) Counter is :" + counterText.getCounters());
		return new ResponseEntity<Counter>(counterText, HttpStatus.OK);
	}	
}