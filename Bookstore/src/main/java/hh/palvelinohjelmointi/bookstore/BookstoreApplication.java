package hh.palvelinohjelmointi.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohjelmointi.bookstore.model.Book;
import hh.palvelinohjelmointi.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("Save a couple of books :)");
			bookRepository.save(new Book("Tieto Kirja", "Jussi Pekka", 2017, "978-3-43-23432-0", 20.99));
			bookRepository.save(new Book("Keitto Opas", "Perttu perä", 2012, "978-2-12-14554-0", 12.34));

			log.info("fetch all books:");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
