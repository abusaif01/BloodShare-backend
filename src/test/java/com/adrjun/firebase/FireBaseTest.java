package com.adrjun.firebase;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class FireBaseTest {

	
	@Test
	public void testGetUui() throws IOException, ExecutionException, InterruptedException
	{
		FireBaseAdmin firebase=new FireBaseAdmin();
		assertNotEquals(null, firebase.getUid("abcd"));
	}
}
