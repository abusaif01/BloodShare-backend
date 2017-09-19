package com.adrjun.firebase;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class FireBaseTest {

	@Test
	public void testInitialization() throws IOException
	{
		FireBaseInitializer ini=new FireBaseInitializer();
		assertEquals(true,ini.initialize());
	}
}
