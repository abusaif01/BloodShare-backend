package com.bloodshare.controller;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bloodshare.entity.BloodSeekEvent;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorLocation;
import com.bloodshare.entity.EventLocation;
import com.bloodshare.filter.TokenFilter;
import com.bloodshare.service.BloodSeekEventSevice;
import com.bloodshare.service.DonorService;
import com.bloodshare.service.DonorServiceImpl;
import com.bloodshare.util.DonorStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodEventTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	private String donorId="1234";
	@Autowired
    private TokenFilter tokeFilter;
	
	@Autowired
	private DonorService donorService;
	
	@Autowired 
	private BloodSeekEventSevice eventService;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				 .addFilter(tokeFilter, "/*")
				.build();
		
//		Donor donor=donorService.getDonorWithId(donorId);
//		System.out.println(donor);
//		if(donor==null)
//		{
//			donor=new Donor();
//			donor.setId(donorId);
//			donor.setBirthDate(new Date());
//			DonorLocation loc=new DonorLocation();
//			loc.setLatitute(0.999999);
//			loc.setLatitute(0.11111);
//			loc.setDonor(donor);
//			donor.setLocation(loc );
//			
//			donor.setBloodGroup("o+e");
//			donor.setMobile("01914820010");
//			donor.setName("Saif");
//			donor.setStatus(DonorStatus.ACTIVE);
//			donor.setFireId("fire1234");
//			
//			((DonorServiceImpl)donorService).createDonor(donor);
//			donorService.startSession("fire1234", donor);
//		}
	}
	
	@Test
	public void eventTest()
	{
		//23.833854, 90.386559
		BloodSeekEvent event=new BloodSeekEvent();
		event.setBooldGroup("0+");
		event.setQunatity(5);
		EventLocation location=new EventLocation();
		location.setLatitute(23.833854);
		location.setLongitute(90.386559);
		event.setLocation(location);
		eventService.createNewEvent(event);
	}
	
}
