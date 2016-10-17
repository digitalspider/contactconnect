package com.spider.jira.contact.connector.data;

public enum Country {
	AUSTRALIA ("Australia", "English", "+61"),
	NZ ("New Zealand", "English", "+64"),
	USA ("United States of America", "English", null),
	UK ("United Kingdom", "English", null),
	PORTUGAL ("Portugal", "Portugese", null),
	POLAND ("Poland", "Polish", null),
	GERMANY ("GERMANY", "German", null),
	MALAYSIA ("Malaysia", "???", null),
	AUSTRIA ("Austria", "German", null),
	CANADA ("Canada", "English", null),
	ESTONIA ("Estonia", "Estonian", null),
	SOUTHAFRICA ("South Africa", "Afrikaans", null),
	SWITZERLAND ("Switzerland", "German", null),
	EGYPT ("Egypt", "Egyptian", null),
	SWEDEN ("Sweden", "Swedish", null),
	SPAIN ("Spain", "Spanish", null),
	FINALND ("Finland", "Finish", null),
	HK ("Hong Kong", "English", null),
	ZAMBIA ("Zambia", "Zambian", null),
	GHANA ("Ghana", "Ghanaian", null),
	INDONESIA ("Indonesia", "Indonesian", null),
	MEXICO ("MEXICO", "Spanish", null);
	
	
	private final String longName;
	private final String language;
	private final String countryPhoneCode;
	Country (String longName, String language, String countryPhoneCode) {
		this.longName = longName;
		this.language = language;
		this.countryPhoneCode = countryPhoneCode;
	}
	public String getLongName() {
		return longName;
	}
	public String getLanguage() {
		return language;
	}
	public String getCountryPhoneCode() {
		return countryPhoneCode;
	}
	
	public static Country parse(String countryString) throws Exception {
		try {
			Country country = Country.valueOf(countryString.toUpperCase());
			return country;
		} catch (IllegalArgumentException e) {
			// ignore try the below
		}
		Country[] countrys = Country.values();
		for (Country country : countrys) {
			if (country.getLongName().equalsIgnoreCase(countryString)) {
				return country;
			}
		}
		throw new Exception("Invalid country value provided: "+countryString);
	}
}

