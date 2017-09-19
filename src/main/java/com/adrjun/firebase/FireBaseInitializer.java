package com.adrjun.firebase;

import java.io.IOException;
import java.io.InputStream;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;

public class FireBaseInitializer {

	public boolean initialize() throws IOException
	{

		InputStream serviceAccount = getClass().getResourceAsStream("/bloodshare-435a4-firebase-adminsdk.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
		  .setDatabaseUrl("https://bloodshare-435a4.firebaseio.com")
		  .build();
		
		FirebaseApp fireBaseApp = FirebaseApp.initializeApp(options);
		System.out.println(fireBaseApp);
		
		return true;
	}
}
