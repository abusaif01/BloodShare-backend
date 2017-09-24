package com.adrjun.firebase;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class FireBaseTest {

	String token="eyJhbGciOiJSUzI1NiIsImtpZCI6IjZhYzBiODBhY2Q3YTYwZTc1YTI4NjhkNjE0ZDIzNmFhNTk1MWVjOTAifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vYmxvb2RzaGFyZS00MzVhNCIsImF1ZCI6ImJsb29kc2hhcmUtNDM1YTQiLCJhdXRoX3RpbWUiOjE1MDYwOTcyNzMsInVzZXJfaWQiOiJ2MUVXdWhpV0U2WUNTREFpdnV5YUFucU9TSGQyIiwic3ViIjoidjFFV3VoaVdFNllDU0RBaXZ1eWFBbnFPU0hkMiIsImlhdCI6MTUwNjA5NzI3NSwiZXhwIjoxNTA2MTAwODc1LCJwaG9uZV9udW1iZXIiOiIrODgwMTkyMDUwMDE3NCIsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsicGhvbmUiOlsiKzg4MDE5MjA1MDAxNzQiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwaG9uZSJ9fQ.NdTU6a8S8Ya49OVHsm-HUcKB4L1H4iRoalkXySTswshlHuhXewWUae9EpZkX1_3nX3IZquB8bF1adTGAMYhkl_UaX7Y6Wdox6VpeK4bhr7izXEAEb7Y8oJdAVDrJoQ7HUBp0FyNKj214qHWeShbep21uyAuORfcgTj5nFOwTwrqzc-YWWEL2wuUcV-YFZx0MPvMXS1pUww4S2RonWFQ2tH7M7SgmBVmZ42r-ZtX8gtk2hAsYczglwIIdBis5I1f_vdudnN_Fork2gA8wMQ9177DY48q6RvfEbxGlPPXD_wTGuHMlm_hETlv2cv8GvamJnDcG-JsazDRU5oAhvKkGaA";
	@Test
	public void testGetUui() throws IOException, ExecutionException, InterruptedException
	{
		FireBaseAdmin firebase=new FireBaseAdmin();
		String uid=firebase.getUid(token);
		assertNotEquals(null, uid);
		assertNotEquals(null, firebase.getUserPhoneNumber(uid));
	}
	
	@Test
	public void delteUser()
	{
		FireBaseAdmin firebase=new FireBaseAdmin();
		firebase.delteUser("XCtKXWg40hZbrxOQJ4trHrJgwvm2");
	}
}
