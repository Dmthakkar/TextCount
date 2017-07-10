package com.inteliment.springrest.model;

/**
 * <h1>Search Text PoJo Class</h1>
 * This is the Pojo Class for Search Text. It contains array of words sent through web-services.
 * @author  Dhaval Thakkar
 * @version 1.0
 */

public class SearchText
{
	private String[] searchText;

	public SearchText() {
		// TODO Auto-generated constructor stub
	}
	
	public SearchText(String[] searchText){
		this.searchText = searchText;
	}
	
	/**
	 * Getter method returns the array of words to search count in paragraph
	 * @return String[] This returns the words to search 
	 */    
	public String[] getSearchText(){
		return searchText;
	}

	/**
	 * Setter method to set array of word to search count in paragraph
	 * @return Nothing
	 */
	public void setSearchText(String[] searchText)
	{
		this.searchText = searchText;
	}

	@Override
	public int hashCode() {
		return searchText.length;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchText st = (SearchText) o;

        if (searchText.length != st.getSearchText().length) return false;
        int i = 0;
        for(String s : searchText){
        	if(s != st.getSearchText()[i++]){
        		return false;
        	}
        }
        return true;
    }
	
	/**
	 * Overriden method of toString
	 * @return String This returns array of words in string format
	 */
	@Override
	public String toString()
	{
		return "[searchText = [" +searchText	+"]";
	}
}