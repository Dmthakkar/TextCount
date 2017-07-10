package com.inteliment.springrest.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.inteliment.springrest.model.Counter;
import com.inteliment.springrest.model.SearchText;
import com.inteliment.springrest.utility.CounterPropertyReader;

/**
 * This is main business class for implemention of Counter Service
 * @author Dhaval Thakkar
 *
 */
@Service("counterService")
public class CounterServiceImpl implements CounterService{
	
	public static String CLASSNAME = "CounterServiceImpl";
    private static final Logger LOG = LoggerFactory.getLogger(CounterServiceImpl.class);
    
	public static Counter counter;
	
	/**
	 * It will populate Map in Counter Object from Paragraph configured in property file
	 */
	static{
		counter = populateCounter();
	}

	/**
	 * This method count same words and prepare map with word and their total count. Logic is in Java8 format
	 * @param input - Paragraph as a String
	 * @return Map<String,Integer> - Count of each word
	 */
	public Map<String, Integer> countJava8(String input){
		LOG.info(CLASSNAME + " countJava8(input) Calling...input is : "+input);
		return Pattern.compile("\\W+")
                 .splitAsStream(input)
                 .collect(Collectors.groupingBy(String::toLowerCase,
                                                Collectors.summingInt(s -> 1)));
	}
	
	/**
	 * This method count same words and prepare map with word and their total count. Logic is in Java7 format
	 * @param input - Paragraph as a String
	 * @return Map<String,Integer> - Count of each word
	 */
	public static Map<String, Integer> wordCount(String input) {
		LOG.info(CLASSNAME + " wordcount(input) Calling...input is : "+input);
	    Map<String,Integer> wordcount = new HashMap<>();
	    try{
		    Matcher matcher = Pattern.compile("\\w+").matcher(input);
		    while (matcher.find()) {
		        String word = matcher.group().toLowerCase();
		        wordcount.put(word, wordcount.getOrDefault(word, 0) + 1);
		    }
	    }catch(Exception e){
	    	LOG.error(CLASSNAME + "Exception in wordCount : "+e,e);
	    }
	    return wordcount;
	}

	/**
	 * This method will Populate counter object on initialization of class.
	 * @return Counter - Object which contains Map of Word count in paragraph from property file
	 */
	private static Counter populateCounter(){
		LOG.info(CLASSNAME + " populateCounter() Calling...");
		Counter counter = new Counter();
		counter.setCounters(wordCount(CounterPropertyReader.getCounterProperty().getParagraph()));
		return counter;
	}

	/**
	 * Get the Default Counter
	 * @return Counter
	 */
	@Override
	public Counter getCounter(){
		return counter;
	}
	
	/**
	 * Method is used to get counter only for given words.
	 * @param SearchText - Object of Search Text which contains array of searching words
	 * @return Counter
	 */
	@Override
	public Counter getCounter(SearchText searchText){
		LOG.info(CLASSNAME + " getCounterList Calling...searchText:"+searchText);
		Counter c = new Counter();
		try{			
			Map<String,Integer> counterText = new LinkedHashMap<>();		
			if(searchText != null){
				for(String s :searchText.getSearchText()){
					counterText.put(s, counter.getCounters().getOrDefault(s.toLowerCase(), 0));
				}
			}
			c.setCounters(counterText);
		}catch(Exception e){
	    	LOG.error(CLASSNAME + "Exception in getCounter : "+e,e);
		}
		return c;
	}
	
	/**
	 * Display only top n numbers which has highest words in paragraph
	 * @param topn - Number to display highest top n words
	 * @return Counter
	 */
	@Override
	public Counter getTopList(long topn){
		LOG.info(CLASSNAME + " getTopList Calling...Topn is "+topn);		
		Counter c = new Counter();
		try{
			Map<String,Integer> orderedText = sortByValue(counter.getCounters(),topn);		
			c.setCounters(orderedText);			
		}catch(Exception e){
	    	LOG.error(CLASSNAME + "Exception in getTopList : "+e,e);
		}

		return c;
	}
	
	/**
	 * Methods is used to sort map by its value and return only top n highest words
	 * @param map
	 * @param topn Display only top n numbers which has highest words in paragraph
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, long topn) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	              .limit(topn)
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
}
