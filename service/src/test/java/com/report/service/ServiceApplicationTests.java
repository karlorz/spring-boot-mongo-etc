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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
			new MongoDBContainer(DockerImageName.parse("mongo:6.0.12"));
	@BeforeAll
	static void setup(@Autowired PostRepository postRepository) {

		Post post1 = new Post("Java-dev", "Full-time",
				new String[]{"Java", "Spring Boot"}, "100000");

		Post post2 = new Post("Python-dev", "Part-time",
				new String[]{"Python", "Django"}, "999");

		List<Post> posts = Arrays.asList(post1,post2);

		postRepository.saveAll(posts);

	}
//	@Test
//	void contextLoads() {
//		log.info("begin test");
//		// Your test logic here
//	}

	@Test
	void getByProfile_ReturnsThePost() {

		final String profile ="Java-dev";
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl= "http://localhost:"+port+"/posts/{profile}";

		// Fetch response as List wrapped in ResponseEntity
		ResponseEntity<Post> getByProfile = restTemplate.exchange(
				resourceUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Post>(){},profile);

		Post post = getByProfile.getBody();

		assertThat(post).isNotNull();
        assert post != null;
        assertThat(post.getProfile()).isEqualTo("Java-dev");
		assertThat(post.getType()).isEqualTo("Full-time");
		assertThat(post.getTechnology()).isEqualTo(new String[]{"Java", "Spring Boot"});
		assertThat(post.getSalary()).isEqualTo("100000");
	}

	@Test
	void updatePost_ReturnsThePost() {
		final String profile ="Java-dev";
		Post postToUpdate = new Post();
		postToUpdate.setProfile(profile);
		postToUpdate.setType("Part-time");
		postToUpdate.setTechnology(new String[]{"Python", "Spring Boot"});
		postToUpdate.setSalary("9999");

		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl= "http://localhost:"+port+"/posts/{profile}";

		// Make the PUT request
		ResponseEntity<Post> updatedResponse = restTemplate.exchange(
				resourceUrl,
				HttpMethod.PUT,
				new HttpEntity<>(postToUpdate),
				new ParameterizedTypeReference<Post>() {},
				profile);

		// Verify the response
		assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Post updatedPost = updatedResponse.getBody();
		assertThat(updatedPost).isNotNull();
        assert updatedPost != null;
        assertThat(updatedPost.getProfile()).isEqualTo(profile);
		assertThat(updatedPost.getType()).isEqualTo("Part-time");
		assertThat(updatedPost.getTechnology()).isEqualTo(new String[]{"Python", "Spring Boot"});
		assertThat(updatedPost.getSalary()).isEqualTo("9999");
	}

}
