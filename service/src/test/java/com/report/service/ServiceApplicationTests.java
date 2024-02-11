package com.report.service;

import com.report.service.documnent.Post;
import com.report.service.repository.PostRepository;
import com.report.service.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"skipDemoData=true"})
@Testcontainers
@Slf4j
class ServiceApplicationTests {

	@Autowired
	private PostService postService;

	@LocalServerPort
	private Integer port;

	@Container
	@ServiceConnection
	public static MongoDBContainer mongoDBContainer =
			new MongoDBContainer(DockerImageName.parse("mongo:6"));
	@BeforeAll
	static void setup(@Autowired PostRepository postRepository) {

		Post post1 = new Post("Java-dev", "Full-time",
				new String[]{"Java", "Spring Boot"}, "100000");

		Post post2 = new Post("Python-dev", "Part-time",
				new String[]{"Python", "Django"}, "999");

		List<Post> posts = Arrays.asList(post1,post2);

		postRepository.saveAll(posts);

	}

	@Test
	void sayHello_ReturnsHelloWorld() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:" + port + "/sayhello";
		String response = restTemplate.getForObject(resourceUrl, String.class);
		assertThat(response).isEqualTo("Hello World!");
	}

	@Test
	void testInvalidCredentials() {
		RestTemplate restTemplate = new RestTemplate();
		String loginUrl = "http://localhost:" + port + "/login";
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, "{\"username\": \"user\", \"password\": \"wrongpassword\"}", String.class);
			fail("Expected HttpClientErrorException$Unauthorized to be thrown");
		} catch (HttpClientErrorException.Unauthorized ex) {
			assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
			assertThat(ex.getResponseBodyAsString()).isEqualTo("");
		}
	}

	@Test
	void testValidCredentials() {
		RestTemplate restTemplate = new RestTemplate();
		String loginUrl = "http://localhost:" + port + "/login";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("username", "user");
		formData.add("password", "password");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, requestEntity, String.class);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			// Add assertions for the response body or further processing as needed
		} catch (HttpClientErrorException.Unauthorized ex) {
			fail("Expected successful response, but got HttpClientErrorException$Unauthorized");
		}
	}

	@Test
	void getByProfile_ReturnsThePost() {
		final String profile = "Java-dev";
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:" + port + "/api/posts/{profile}";

		String loginUrl = "http://localhost:" + port + "/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("username", "user");
		formData.add("password", "password");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, requestEntity, String.class);

			// Retrieve the session ID from the response cookies
			String sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

			// Create a new HttpHeaders object and set the session ID as a cookie
			HttpHeaders sessionHeaders = new HttpHeaders();
			sessionHeaders.set(HttpHeaders.COOKIE, sessionId);

			// Create a new HttpEntity with the session headers
			HttpEntity<Void> sessionEntity = new HttpEntity<>(sessionHeaders);

			// Fetch response as Post object wrapped in ResponseEntity, including the session headers
			ResponseEntity<Post> getByProfile = restTemplate.exchange(
					resourceUrl,
					HttpMethod.GET,
					sessionEntity,
					Post.class,
					profile
			);

			Post post = getByProfile.getBody();

			assertThat(post).isNotNull();
			assertThat(post.getProfile()).isEqualTo("Java-dev");
			assertThat(post.getType()).isEqualTo("Full-time");
			assertThat(post.getTechnology()).isEqualTo(new String[]{"Java", "Spring Boot"});
			assertThat(post.getSalary()).isEqualTo("100000");
		} catch (HttpClientErrorException.Unauthorized ex) {
			fail("Expected successful response, but got HttpClientErrorException$Unauthorized");
		}
	}

	@Test
	void updatePost_ReturnsThePost() {
		final String profile = "Java-dev";
		Post postToUpdate = new Post();
		postToUpdate.setProfile(profile);
		postToUpdate.setType("Part-time");
		postToUpdate.setTechnology(new String[]{"Python", "Spring Boot"});
		postToUpdate.setSalary("9999");

		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:" + port + "/api/posts/{profile}";

		String loginUrl = "http://localhost:" + port + "/login";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("username", "user");
		formData.add("password", "password");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, requestEntity, String.class);

		// Retrieve the session ID from the response cookies
		String sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

		// Create a new HttpHeaders object and set the session ID as a cookie
		HttpHeaders sessionHeaders = new HttpHeaders();
		sessionHeaders.set(HttpHeaders.COOKIE, sessionId);

		// Create a new HttpEntity with the session headers and the post to update
		HttpEntity<Post> requestEntityWithSession = new HttpEntity<>(postToUpdate, sessionHeaders);

		// Make the PUT request with the session headers and the updated post
		ResponseEntity<Post> updatedResponse = restTemplate.exchange(
				resourceUrl,
				HttpMethod.PUT,
				requestEntityWithSession,
				Post.class,
				profile
		);

		// Verify the response
		assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Post updatedPost = updatedResponse.getBody();
		assertThat(updatedPost).isNotNull();
		assertThat(updatedPost.getProfile()).isEqualTo(profile);
		assertThat(updatedPost.getType()).isEqualTo("Part-time");
		assertThat(updatedPost.getTechnology()).isEqualTo(new String[]{"Python", "Spring Boot"});
		assertThat(updatedPost.getSalary()).isEqualTo("9999");
	}
}
