package com.adrjun.firebase;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

public class FireBaseAdmin implements OnFailureListener
{

	private static final Logger logger = LoggerFactory.getLogger(FireBaseAdmin.class);
	static
	{
		InputStream serviceAccount = null;
		try
		{
			serviceAccount = FireBaseAdmin.class.getResourceAsStream("/bloodshare-435a4-firebase-adminsdk.json");
			logger.info(""+serviceAccount);
			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl("https://bloodshare-435a4.firebaseio.com")
				.build();
			
			FirebaseApp fireBaseApp = FirebaseApp.initializeApp(options);
			System.out.println(fireBaseApp);
		}catch(IOException e)
		{
			e.printStackTrace();
			logger.error("FireBase not initialized.");
		}
		finally 
		{
			try {
				if(serviceAccount!=null)
				serviceAccount.close();
			} catch (IOException e) {
				logger.error("Consicutive erro in FireBase initialization. Exiting System with 0");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public String getUid(String token) throws ExecutionException, InterruptedException
	{
		logger.debug("Authenticating token : "+token);
		Task<FirebaseToken> authenticationTask = FirebaseAuth.getInstance().verifyIdToken(token)
			.addOnSuccessListener(new OnSuccessListener<FirebaseToken>()
			{
				@Override
				public void onSuccess(FirebaseToken decodedToken) {
					String uid = decodedToken.getUid();
					logger.debug("Authentication success. UID : "+uid);
				}
			})
			.addOnFailureListener(new OnFailureListener() {
				
				@Override
				public void onFailure(Exception authenticationException) {
					logger.debug("User could not be authernticated : "+authenticationException);
				}
			});
		
		logger.debug("Before await");
		Tasks.await(authenticationTask);
		logger.debug("Returning  "+authenticationTask.getResult().getUid());
		
		return authenticationTask.getResult().getUid();
	}
	
	public String getUserPhoneNumber(String uid) throws ExecutionException, InterruptedException
	{
		Task<UserRecord> task = FirebaseAuth.getInstance().getUser(uid)
				.addOnSuccessListener(new OnSuccessListener<UserRecord>() {

					@Override
					public void onSuccess(UserRecord userRecord) {
						logger.debug("User record retrived : "+userRecord);
					}
				})
			    .addOnFailureListener(this);
		Tasks.await(task);
		return task.getResult().getPhoneNumber();
	}

	@Override
	public void onFailure(Exception excpetion) {
		excpetion.printStackTrace();
	}
}
