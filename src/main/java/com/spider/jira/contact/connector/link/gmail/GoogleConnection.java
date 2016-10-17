package com.spider.jira.contact.connector.link.gmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.schema.Person;
import com.google.gdata.client.contacts.ContactQuery;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.spider.jira.contact.connector.base.BaseContactInterface;
import com.spider.jira.contact.connector.base.BaseContactInterfacePresent;
import com.spider.jira.contact.connector.core.ContactEngine;
import com.spider.jira.contact.connector.exception.ConnectionException;
import com.spider.jira.contact.connector.iface.ContactConnection;

public class GoogleConnection extends BaseContactInterfacePresent<ContactsService,ContactEntry> implements ContactConnection<ContactsService,ContactEntry> {
	
	private ContactsService googleService;

	public static final String LINKEDIN_DEV_URL = "https://graph.facebook.com/";
	public static final String LINKEDIN_OAUTH_TOKEN_REQUEST = LINKEDIN_DEV_URL + "oauth/access_token";
	
	// https://graph.facebook.com/oauth/access_token?client_id=12605646127&client_secret=7f3218f07f7d5a43762e632ebd79e619&grant_type=client_credentials
	// https://graph.facebook.com/app?access_token=12605646127|RguZJQF2wLtdIQViZZ9tloUmfRU
	
	private String LINKEDIN_APP_ID = "12605646127";
	private String LINKEDIN_APP_SECRET = "7f3218f07f7d5a43762e632ebd79e619";

	/**
	 * To get the necessary token for the below method, 
	 * you can also do the following in a web browser.
	 * 
	 * 1. Go to http://developers.facebook.com/docs/reference/rest/
	 * 2. Get Zuckerburgs Address
	 * 3. https://api.facebook.com/method/users.getInfo?uids=4&fields=name&access_token=AAAAAAITEghMBAJlED8ZBYiGRbM78djpdA1dGrALKWTpyILR3bLHGKErGvuRP8zi0W4c3ecdD7uA9MMAHf2KwgrnPmZAHCaKl0ZAPCXCZAgZDZD&format=json
	 * 4. Pull our the token string = AAAAAAITEghMBAJlED8ZBYiGRbM78djpdA1dGrALKWTpyILR3bLHGKErGvuRP8zi0W4c3ecdD7uA9MMAHf2KwgrnPmZAHCaKl0ZAPCXCZAgZDZD
	 * 
	 * After this you can make requests using:
	 * https://graph.facebook.com/me/friends?access_token=xxx
	 * https://graph.facebook.com/2527992?access_token=
	 * https://graph.facebook.com/547995314?fields=id,name&access_token=
	 * https://graph.facebook.com/me?metadata=1&access_token=
	 * https://graph.facebook.com/me/friends&access_token=
	 * 
	 * Test using the graph explorer tool
	 * http://developers.facebook.com/tools/explorer?method=GET&path=547995314%3Ffields%3Did%2Cname
	 * http://developers.facebook.com/tools/explorer?method=GET&path=me%2Ffriends
	 * 
	 */
	public GoogleConnection(BaseContactInterface<LinkedInApiClient,Person> contactInterface) {
		super(contactInterface);
	}

	@Override
	public LinkedInApiClient getConnection() throws ConnectionException {
		try {
			if (googleService != null) {
				return googleService;
			}

			OAuthService service = new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(LINKEDIN_APP_ID).apiSecret(LINKEDIN_APP_SECRET).build();
			Token token = service.getRequestToken();
			String authUrl = service.getAuthorizationUrl(token);
			Token accessToken = service.getAccessToken(token, null);
			
			LinkedInApiClientFactory linkedInApiClientFactory = LinkedInApiClientFactory.newInstance(LINKEDIN_APP_ID, LINKEDIN_APP_SECRET); 
			googleService = linkedInApiClientFactory.createLinkedInApiClient(token.getToken(),"");
			return googleService;
//			if (facebookClient != null) {
//				return facebookClient;
//			}
//			
//			
//			// Make HTTP Token Request
//			String url = getFacebookTokenRequestURL();
//			System.out.println(url);
//			String result = callHttp(url);
//			System.out.println(result);
//			if (result != null) {
//				String[] tokenStrings = result.split("=");
//				if (tokenStrings.length == 2) {
//					String tokenKey = tokenStrings[0];
//					String tokenValue = tokenStrings[1];
//					if (tokenKey.equals("access_token")) {
//						// token comes from http://developers.facebook.com/tools/explorer?method=GET&path=547995314%3Ffields%3Did%2Cname
//						String token = "AAACEdEose0cBAIGu83Q1TJYjFjpTwLwgZAcAM1Euey7ZB3HpVkq5mN2wxzEs5xZCE7ZC8BklZCfZCTqyOS8menG86oQWdsetMB3jUtkMtgsQZDZD";
//						//String token = tokenValue;
//						facebookClient = new DefaultFacebookClient(token);
//						return facebookClient;
//					} else {
//						throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
//					}
//				} else {
//					throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
//				}			
//			} else {
//				throw new ConnectionException("access_token parameter not returned from URL. URL="+url);
//			}
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
	}	

	public String callHttp(String url) {
		StringBuffer result = new StringBuffer();
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(instream));
					String line = null;
					while ((line = reader.readLine()) != null) {
						result.append(line);
					}
				} catch (IOException ex) {
					throw ex;
				} catch (RuntimeException ex) {
					httpget.abort();
					throw ex;
				} finally {
					instream.close();
				}
				httpclient.getConnectionManager().shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
