import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Book, BookService } from './book.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})

export class App {
	books: Book[] = [];
	newBook: Book = { title: '', author: '' };
	selectedBook: Book | null = null;

	constructor(private bookService: BookService) {
		this.loadBooks();
	}

	loadBooks(): void {
		this.bookService.getBooks().subscribe({
			next: (data) => {
				this.books = data;
			},
			error: (err) => console.error('Failed to load books', err),
		});
	}

	viewBook(id: number): void {
		this.bookService.getBook(id).subscribe({
			next: (book) => {
				this.selectedBook = book;
			},
			error: (err) => console.error('Failed to load books', err),
		});
	}

	addBook(): void {
		if (!this.newBook.title || !this.newBook.author)
			return;
		this.bookService.addBook(this.newBook).subscribe({
			next: () => {
				this.newBook = { title: '', author: '' };
				this.loadBooks();
			},
			error: (err) => console.error('Failed to add book', err),
		});
	}

	deleteBook(id: number): void {
		this.bookService.deleteBook(id).subscribe({
			next: () => this.loadBooks(),
			error: (err) => console.error('Failed to delete book', err),
		});
	}
}