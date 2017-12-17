package com.bloodshare.service;



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
import com.bloodshare.entity.Hospital;
import com.bloodshare.filter.TokenFilter;

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
		Hospital location=new Hospital();
		location.setLatitude(23.833854);
		location.setLongitude(90.386559);
		event.setHospital(location);
		eventService.createNewEvent(event);
	}
	
}
