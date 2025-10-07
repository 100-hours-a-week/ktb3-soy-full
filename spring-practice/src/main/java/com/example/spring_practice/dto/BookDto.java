package com.example.spring_practice.dto;

public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String isbn;

    public BookDto() {}

    public BookDto(long id, String title, String author, String description, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
    }

    public BookDto(String title, String author, String description, String isbn) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
    }

    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}
    public String getAuthor() {return this.author;}
    public void setAuthor(String author) {this.author = author;}
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
    public String getIsbn() {return this.isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    @Override
    public String toString() {
        return String.format("[%d] Book title[%s] written by [%s] is about [%s]", this.id, this.title, this.author, this.description);
    }
}
