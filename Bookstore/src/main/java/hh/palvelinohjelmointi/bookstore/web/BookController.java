package hh.palvelinohjelmointi.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.palvelinohjelmointi.bookstore.model.Book;
import hh.palvelinohjelmointi.bookstore.model.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookRepository drepository;

	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookStoreController(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}

	// ADD BOOK
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	// SAVE BOOK
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	// DELETE BOOK
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}

	// EDIT BOOK
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("departsments", drepository.findAll());
		return "editbook";
	}
}
