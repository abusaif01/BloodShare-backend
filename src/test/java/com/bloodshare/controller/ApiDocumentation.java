
package com.bloodshare.controller;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bloodshare.dao.DonorDAO;
import com.bloodshare.entity.Donor;
import com.bloodshare.entity.DonorLocation;
import com.bloodshare.service.DonorService;
import com.bloodshare.service.DonorServiceImpl;
import com.bloodshare.util.DonorStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ApiDocumentation {
	
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@Autowired
	private DonorService donorService;
//
//	@Autowired
//	private TagRepository tagRepository;
//
//	@Autowired
	private ObjectMapper objectMapper=new ObjectMapper() ;
//
	@Autowired
	private WebApplicationContext context;
//
	private MockMvc mockMvc;
	private String donorId="4a7d8f96-01ba-4be9-91c3-32979f06925a";
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)) 
				.build();
		
		Donor donor=donorService.getDonorWithId(donorId);
		System.out.println(donor);
		if(donor==null)
		{
			donor=new Donor();
			donor.setId(donorId);
			donor.setBirthDate(new Date());
			DonorLocation loc=new DonorLocation();
			loc.setLatitute(0.999999);
			loc.setLatitute(0.11111);
			loc.setDonor(donor);
			donor.setLocation(loc );
			
			donor.setBloodGroup("o+e");
			donor.setMobile("01914820010");
			donor.setName("Saif");
			donor.setStatus(DonorStatus.ACTIVE);
			donor.setFireId("fire1234");
			
			((DonorServiceImpl)donorService).createDonor(donor);
		}
	}
	
	@Test
	public void helloTest() throws Exception
	{
		this.mockMvc.perform(get("/")) 
		.andExpect(status().isOk()) 
		.andDo(document("hello"));
	}
//	
////	@Test
////	public void isNewAndSendOtpTest() throws Exception
////	{
////		this.mockMvc.perform(get("/user/check_isnew_send_otp?mobile=01914820010"))
////		.andExpect(status().isOk()) 
////		.andDo(document("inNewNOtp", requestParameters(parameterWithName("mobile").description("Donor Mobile Number")) ));
////	}
//	
////	@Test
////	public void authenticateTest() throws Exception
////	{
////		String token="1234";
////		this.mockMvc.perform(post("/user/authenticate").contentType(MediaType.APPLICATION_JSON)
////				.content(token) ) 
////		.andExpect(status().isUnauthorized()) 
////		.andDo(document("authenticate"));
////	}
//	
//	@Test
//	public void getUserTest() throws Exception
//	{
//		this.mockMvc.perform(get("/user/4a7d8f96-01ba-4be9-91c3-32979f06925a")) 
//		.andExpect(status().isOk()) 
//		.andDo(document("getDonor"));
//	}
//	
//	@Test
//	public void getUserWithMobileTest1() throws Exception
//	{
//		this.mockMvc.perform(get("/user/01914820010?type=2")) 
//		.andExpect(status().isOk()) 
//		.andDo(document("getDonorMobile1"));
//	}
//	
//	@Test
//	public void getUserWithMobileTest2() throws Exception
//	{
//		this.mockMvc.perform(get("/user/01914820010?type=m")) 
//		.andExpect(status().isOk()) 
//		.andDo(document("getDonorMobile2"));
//	}
//	@Test
//	public void getUserWithMobileTest3() throws Exception
//	{
//		this.mockMvc.perform(get("/user/01914820010?type=mobile")) 
//		.andExpect(status().isOk()) 
//		.andDo(document("getDonorMobile3"));
//	}
//	
//	@Test
//	public void updateUserTest() throws Exception
//	{
//		Map<String, String> donorData = new HashMap<String, String>();
//		donorData.put("mobile", "01914820010");
//		donorData.put("name", "Saif");
//		donorData.put("bloodGroup", "O+ve");
//		donorData.put("birthDate", "10-09-1991");
//		this.mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
//				.content(this.objectMapper.writeValueAsString(donorData))) 
//		.andExpect(status().isOk()) 
//		.andDo(document("updateUser"));
//	}
//	
//	@Test
//	public void crateEventTest() throws Exception
//	{
//		Map<String, String> eventData = new HashMap<String, String>();
//		eventData.put("quantity", "5");
//		eventData.put("location", "dhaka");
//		eventData.put("bloodGroup", "O+ve");
//		eventData.put("birthDate", "19-09-2017");
//		
//		this.mockMvc.perform(post("/bloodSeekEvent").contentType(MediaType.APPLICATION_JSON)
//				.content(this.objectMapper.writeValueAsString(eventData))) 
//		.andExpect(status().isCreated()) 
//		.andDo(document("createEvent"));
//	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
//
//	@Test
//	public void errorExample() throws Exception {
//		this.mockMvc
//				.perform(get("/error")
//						.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
//						.requestAttr(RequestDispatcher.ERROR_REQUEST_URI,
//								"/notes")
//						.requestAttr(RequestDispatcher.ERROR_MESSAGE,
//								"The tag 'http://localhost:8080/tags/123' does not exist"))
//				.andDo(print()).andExpect(status().isBadRequest())
//				.andExpect(jsonPath("error", is("Bad Request")))
//				.andExpect(jsonPath("timestamp", is(notNullValue())))
//				.andExpect(jsonPath("status", is(400)))
//				.andExpect(jsonPath("path", is(notNullValue())))
//				.andDo(document("error-example",
//						responseFields(
//								fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
//								fieldWithPath("message").description("A description of the cause of the error"),
//								fieldWithPath("path").description("The path to which the request was made"),
//								fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
//								fieldWithPath("timestamp").description("The time, in milliseconds, at which the error occurred"))));
//	}
//
//	@Test
//	public void indexExample() throws Exception {
//		this.mockMvc.perform(get("/"))
//			.andExpect(status().isOk())
//			.andDo(document("index-example",
//					links(
//							linkWithRel("notes").description("The <<resources-notes,Notes resource>>"),
//							linkWithRel("tags").description("The <<resources-tags,Tags resource>>"),
//							linkWithRel("profile").description("The ALPS profile for the service")),
//					responseFields(
//							fieldWithPath("_links").description("<<resources-index-links,Links>> to other resources"))));
//
//	}
//
//	@Test
//	public void notesListExample() throws Exception {
//		this.noteRepository.deleteAll();
//
//		createNote("REST maturity model",
//				"http://martinfowler.com/articles/richardsonMaturityModel.html");
//		createNote("Hypertext Application Language (HAL)",
//				"http://stateless.co/hal_specification.html");
//		createNote("Application-Level Profile Semantics (ALPS)", "http://alps.io/spec/");
//
//		this.mockMvc.perform(get("/notes"))
//			.andExpect(status().isOk())
//			.andDo(document("notes-list-example",
//					links(
//							linkWithRel("self").description("Canonical link for this resource"),
//							linkWithRel("profile").description("The ALPS profile for this resource")),
//					responseFields(
//							fieldWithPath("_embedded.notes").description("An array of <<resources-note, Note resources>>"),
//							fieldWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources"))));
//	}
//
//	@Test
//	public void notesCreateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//				.perform(
//						post("/tags").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "http://martinfowler.com/articles/richardsonMaturityModel.html");
//		note.put("tags", Arrays.asList(tagLocation));
//
//		this.mockMvc.perform(
//				post("/notes").contentType(MediaTypes.HAL_JSON).content(
//						this.objectMapper.writeValueAsString(note))).andExpect(
//				status().isCreated())
//				.andDo(document("notes-create-example",
//						requestFields(
//									fieldWithPath("title").description("The title of the note"),
//									fieldWithPath("body").description("The body of the note"),
//									fieldWithPath("tags").description("An array of tag resource URIs"))));
//	}
//
//	@Test
//	public void noteGetExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//				.perform(
//						post("/tags").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "http://martinfowler.com/articles/richardsonMaturityModel.html");
//		note.put("tags", Arrays.asList(tagLocation));
//
//		String noteLocation = this.mockMvc
//				.perform(
//						post("/notes").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(note)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		this.mockMvc.perform(get(noteLocation))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("title", is(note.get("title"))))
//			.andExpect(jsonPath("body", is(note.get("body"))))
//			.andExpect(jsonPath("_links.self.href", is(noteLocation)))
//			.andExpect(jsonPath("_links.tags", is(notNullValue())))
//			.andDo(print())
//			.andDo(document("note-get-example",
//					links(
//							linkWithRel("self").description("Canonical link for this <<resources-note,note>>"),
//							linkWithRel("note").description("This <<resources-note,note>>"),
//							linkWithRel("tags").description("This note's tags")),
//					responseFields(
//							fieldWithPath("title").description("The title of the note"),
//							fieldWithPath("body").description("The body of the note"),
//							fieldWithPath("_links").description("<<resources-note-links,Links>> to other resources"))));
//	}
//
//	@Test
//	public void tagsListExample() throws Exception {
//		this.noteRepository.deleteAll();
//		this.tagRepository.deleteAll();
//
//		createTag("REST");
//		createTag("Hypermedia");
//		createTag("HTTP");
//
//		this.mockMvc.perform(get("/tags"))
//			.andExpect(status().isOk())
//			.andDo(document("tags-list-example",
//					links(
//							linkWithRel("self").description("Canonical link for this resource"),
//							linkWithRel("profile").description("The ALPS profile for this resource")),
//					responseFields(
//							fieldWithPath("_embedded.tags").description("An array of <<resources-tag,Tag resources>>"),
//							fieldWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources"))));
//	}
//
//	@Test
//	public void tagsCreateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		this.mockMvc.perform(
//				post("/tags").contentType(MediaTypes.HAL_JSON).content(
//						this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated())
//				.andDo(document("tags-create-example",
//						requestFields(
//								fieldWithPath("name").description("The name of the tag"))));
//	}
//
//	@Test
//	public void noteUpdateExample() throws Exception {
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "http://martinfowler.com/articles/richardsonMaturityModel.html");
//
//		String noteLocation = this.mockMvc
//				.perform(
//						post("/notes").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(note)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		this.mockMvc.perform(get(noteLocation)).andExpect(status().isOk())
//				.andExpect(jsonPath("title", is(note.get("title"))))
//				.andExpect(jsonPath("body", is(note.get("body"))))
//				.andExpect(jsonPath("_links.self.href", is(noteLocation)))
//				.andExpect(jsonPath("_links.tags", is(notNullValue())));
//
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//				.perform(
//						post("/tags").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		Map<String, Object> noteUpdate = new HashMap<String, Object>();
//		noteUpdate.put("tags", Arrays.asList(tagLocation));
//
//		this.mockMvc.perform(
//				patch(noteLocation).contentType(MediaTypes.HAL_JSON).content(
//						this.objectMapper.writeValueAsString(noteUpdate)))
//				.andExpect(status().isNoContent())
//				.andDo(document("note-update-example",
//						requestFields(
//								fieldWithPath("title").description("The title of the note").type(JsonFieldType.STRING).optional(),
//								fieldWithPath("body").description("The body of the note").type(JsonFieldType.STRING).optional(),
//								fieldWithPath("tags").description("An array of tag resource URIs").optional())));
//	}
//
//	@Test
//	public void tagGetExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//				.perform(
//						post("/tags").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		this.mockMvc.perform(get(tagLocation))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("name", is(tag.get("name"))))
//			.andDo(document("tag-get-example",
//					links(
//							linkWithRel("self").description("Canonical link for this <<resources-tag,tag>>"),
//							linkWithRel("tag").description("This <<resources-tag,tag>>"),
//							linkWithRel("notes").description("The <<resources-tagged-notes,notes>> that have this tag")),
//					responseFields(
//							fieldWithPath("name").description("The name of the tag"),
//							fieldWithPath("_links").description("<<resources-tag-links,Links>> to other resources"))));
//	}
//
//	@Test
//	public void tagUpdateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//				.perform(
//						post("/tags").contentType(MediaTypes.HAL_JSON).content(
//								this.objectMapper.writeValueAsString(tag)))
//				.andExpect(status().isCreated()).andReturn().getResponse()
//				.getHeader("Location");
//
//		Map<String, Object> tagUpdate = new HashMap<String, Object>();
//		tagUpdate.put("name", "RESTful");
//
//		this.mockMvc.perform(
//				patch(tagLocation).contentType(MediaTypes.HAL_JSON).content(
//						this.objectMapper.writeValueAsString(tagUpdate)))
//				.andExpect(status().isNoContent())
//				.andDo(document("tag-update-example",
//						requestFields(
//								fieldWithPath("name").description("The name of the tag"))));
//	}
//
//	private void createNote(String title, String body) {
//		Note note = new Note();
//		note.setTitle(title);
//		note.setBody(body);
//
//		this.noteRepository.save(note);
//	}
//
//	private void createTag(String name) {
//		Tag tag = new Tag();
//		tag.setName(name);
//		this.tagRepository.save(tag);
//	}
}
