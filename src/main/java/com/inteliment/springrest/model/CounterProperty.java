package com.inteliment.springrest.model;

/**
 * This class is used to configure dynamic data from property file.
 * @author Dhaval Thakkar
 *
 */
public class CounterProperty {
	private String paragraph;
	private String userName;
	private String password;
	
	/**
	 * Get Paragraph data from property file
	 * @return paragraph
	 */
	public String getParagraph() {
		return paragraph;
	}
	
	/**
	 * Set Paragraph data
	 * @param paragraph
	 */
	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	
	/**
	 * Get Username from property file
	 * @return username
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * set username 
	 * @param userName
	 */
	public void setuserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * get Password from property file
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "Paragraph: "+ getParagraph() + " username: " + getUserName() + " password:" + getPassword();
	}
}