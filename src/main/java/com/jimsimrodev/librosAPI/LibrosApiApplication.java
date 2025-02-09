package com.jimsimrodev.librosAPI;

import com.jimsimrodev.librosAPI.principal.Main;
import com.jimsimrodev.librosAPI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosApiApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(LibrosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.run();
	}
}
