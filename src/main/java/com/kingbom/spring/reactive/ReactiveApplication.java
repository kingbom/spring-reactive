package com.kingbom.spring.reactive;

import com.kingbom.spring.reactive.domain.Transaction;
import com.kingbom.spring.reactive.repository.TransactionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Bean
	ApplicationRunner init(TransactionRepository repository) {
		String  userId = UUID.randomUUID().toString();

		Object[][] data = {
				{ null, userId, UUID.randomUUID().toString(), 305, Instant.now()},
				{ null, userId, UUID.randomUUID().toString(), 404, Instant.now()},
				{ null, userId, UUID.randomUUID().toString(), 503, Instant.now()},
				{ null, userId, UUID.randomUUID().toString(), 602, Instant.now()},
				{ null, userId, UUID.randomUUID().toString(), 701, Instant.now()}
		};

		return args -> {
			repository
					.deleteAll()
					.thenMany(
							Flux.just(data)
								.map(array -> new Transaction((String) array[0], (String) array[1], (String) array[2], (Integer) array[3], (Instant) array[4]))
								.flatMap(repository::save)
					)
					.thenMany(repository.findAll())
					.subscribe(transaction -> System.out.println(String.format("saved -> %s", transaction.toString())));

		};
	}

}
