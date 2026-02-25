package com.library.backend;

public class Book {
    private static Long numBooks = 0L;

    private final Long id;

    public Book() {
        this.id = ++numBooks;
    }

    public Book(String title, String author, String isbn) {
        this();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    private String title;
    private String author;
    private String isbn;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}