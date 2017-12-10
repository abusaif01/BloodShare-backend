package com.adrjun.firebase.fcm;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


import com.fasterxml.jackson.databind.ObjectMapper;

public class FCMWrapperApi {
	
	public boolean sendNotificationtToDevice(FCMPayLoad fcmPayLoad) throws ClientProtocolException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(fcmPayLoad);
	
		String postUrl = "https://fcm.googleapis.com/fcm/send";
		String fcmPayloadInJsonFormat = "";
		fcmPayloadInJsonFormat = mapper.writeValueAsString(fcmPayLoad);
		

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(postUrl);
		StringEntity fcmPaylodBody;
		fcmPaylodBody = new StringEntity (fcmPayloadInJsonFormat);		
		post.setEntity(fcmPaylodBody);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "key=AIzaSyC46gX8ZmU14UVZWTuNW_7G-UzchMQICWU");
		post.setEntity(fcmPaylodBody);
		
		HttpResponse  response = httpClient.execute(post);
		System.out.println(response);
		
		return true;		
	}

}
