package com.spider.jira.contact.connector.bean;

import com.spider.jira.contact.connector.data.Country;

public class Address {

	private String line1;
	private String line2;
	private String line3;
	private String suburb;
	private String postcode;
	private String state;
	private Country country;

	public String toString() {
		String s = "Address="+line1+","+line2+","+line3+
			" - "+suburb+","+state+","+postcode+","+country;
		return s;
	}

	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
