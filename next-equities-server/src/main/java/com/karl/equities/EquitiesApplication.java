package com.karl.equities;

import com.karl.equities.auth.AuthenticationService;
import com.karl.equities.auth.RegisterRequest;
import com.karl.equities.book.BookRequest;
import com.karl.equities.book.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.karl.equities.user.Role.ADMIN;
import static com.karl.equities.user.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EquitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquitiesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			BookService bookService
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.username("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			var book1 = BookRequest.builder()
					.author("John Doe")
					.isbn("1234567890")
					.build();
			bookService.save(book1);
			System.out.println("Book 1: "+ book1.getAuthor());

			var book2 = BookRequest.builder()
					.author("Jane Smith")
					.isbn("0987654321")
					.build();
			bookService.save(book2);
			System.out.println("Book 2: "+ book2.getAuthor());

		};
	}
}