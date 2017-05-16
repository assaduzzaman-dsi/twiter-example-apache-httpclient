package com.example;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Hello world!
 *
 */
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class JavaRestTweet {
	static String consumerKeyStr = "xxx";
	static String consumerSecretStr = "xxx";
	static String accessTokenStr = "xxx-xxx";
	static String accessTokenSecretStr = "xxx";


	public static void main(String[] args) throws Exception {
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

		HttpGet httpGet = new HttpGet(
				"https://api.twitter.com/1.1/search/tweets.json?q=%23taito_na");

		oAuthConsumer.sign(httpGet);

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httpResponse = httpClient.execute(httpGet);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode + ':'
				+ httpResponse.getStatusLine().getReasonPhrase());
		System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));

	}
	
	public static void post() throws Exception {
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,
				consumerSecretStr);
		oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

		HttpPost httpPost = new HttpPost(
				"http://api.twitter.com/1.1/statuses/update.json?status=Hello%20Twitter%20World.");

		oAuthConsumer.sign(httpPost);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode + ':'
				+ httpResponse.getStatusLine().getReasonPhrase());
		System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));

	}
	
	//Deprecated
	//HttpClient httpClient = new DefaultHttpClient(); 
/*
	HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

	try {

	    HttpPost request = new HttpPost("http://yoururl");
	    StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
	    request.addHeader("content-type", "application/x-www-form-urlencoded");
	    request.setEntity(params);
	    HttpResponse response = httpClient.execute(request);

	    //handle response here...

	}catch (Exception ex) {

	    //handle exception here

	} finally {
	    //Deprecated
	    //httpClient.getConnectionManager().shutdown(); 
	}
*/
}