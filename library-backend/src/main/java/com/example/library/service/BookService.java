package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
	private final BookRepository repository;

	public BookService(BookRepository repository) {
		this.repository = repository;
	}

	public List<Book> findAll() {
		return repository.findAll();
	}

	public Optional<Book> find(Long id) {
		return repository.findById(id);
	}

	public Book create(Book book) {
		return repository.save(book);
	}

	public Book update(Long id, Book bookDetails) {
		Book book = repository.findById(id).orElseThrow();
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		return repository.save(book);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}