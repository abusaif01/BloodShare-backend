package com.adrjun.firebase.fcm;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

public class FCMWrapperApiTest {
	
	@Test
	public void wrapperApiTest() throws ClientProtocolException, IOException{
		FCMWrapperApi fcmWrapperApi = new FCMWrapperApi();
		
		FCMPayLoad fcmPayLoad = null;
		fcmWrapperApi.sendNotificationtToDevice(fcmPayLoad);
	}

}
