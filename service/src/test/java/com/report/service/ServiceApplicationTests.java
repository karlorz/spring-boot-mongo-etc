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
import org.springframework.http.HttpMethod;
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
		assertThat(post.getProfile()).isEqualTo("Java-dev");
		assertThat(post.getType()).isEqualTo("Full-time");
		assertThat(post.getTechnology()).isEqualTo(new String[]{"Java", "Spring Boot"});
		assertThat(post.getSalary()).isEqualTo("100000");
	}

}
