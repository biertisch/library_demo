package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {
	"http://127.0.0.1:4200",
	"http://localhost:4200"
})
public class BookController {
	private final BookService service;

	public BookController(BookService service) {
		this.service = service;
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		Optional<Book> book = service.find(id);
		if (book.isPresent())
			return ResponseEntity.ok(book.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return service.create(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		return service.update(id, book);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		service.delete(id);
	}
}