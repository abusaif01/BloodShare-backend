package com.bloodshare.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonorServiceTest {

	@Autowired
	DonorService service;
	
	@Test
	public void testReadOperarion()
	{
		System.out.println(service.getDonorWithId("sdafds"));
		assertEquals(null, service.getDonorWithId("sdafds"));
	}
}
